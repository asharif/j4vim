package org.orphanware.j4vim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.orphanware.j4vim.ds.Node;
import org.orphanware.j4vim.ds.Trie;
import java.lang.reflect.Method;
import java.lang.ClassNotFoundException;


public class MethodComplete {

	private Map<String, String> varClassMap;
	private Map<String, Trie> classMethodTrieMap;

	public MethodComplete(String code) { 

		initDataStruct(code);
	}

	/**
	 * inits the method complete datastructures
	 **/
	public void initDataStruct(String code) {

		varClassMap = new HashMap<>();
		classMethodTrieMap = new HashMap();

		Matcher m = Pattern.compile("\\s*(\\w+)\\s+(\\w+)\\s*=").matcher(code);

		while(m.find()) {

			String className = m.group(1);
			String varName = m.group(2);
			String packageName = getPackageNameOfClass(className, code);
			System.out.println(varName + ":" + packageName + "." + className);
			varClassMap.put(varName, packageName + "." + className);
			addMethodTrie(packageName, className);
		} 


	}

	private String getPackageNameOfClass(String className, String code) {

		Matcher m = Pattern.compile("import\\s+(([a-zA-Z_$][a-zA-Z\\d_$]*\\.)*)" + className).matcher(code);

		String packageName = "";
		while(m.find()) {

			packageName = m.group(1);
			int lastPeriod = packageName.lastIndexOf(".");
			packageName = packageName.substring(0, lastPeriod);

		}


		return packageName;

	}

	public void addMethodTrie(String packageName, String className) {

		try {

			Class c = Class.forName(packageName + "." + className);
			Method methods[] = c.getMethods();
			Trie trie = new Trie();

			for (int i = 0; i < methods.length; i++) {
				String methodStub = methods[i].toString();
				Matcher m = Pattern.compile(".(\\w+\\(\\S*\\))").matcher(methodStub);
				
				while(m.find()) {
					
					String method = m.group(1);
					Node node = new Node(method);
					trie.add(node);

				}

			}

		classMethodTrieMap.put(packageName + "." + className, trie);

		} catch (ClassNotFoundException e){
			System.out.println("could not find class: " + className);
		} catch (Exception e) {
		}


	}


	public String getMethodsForVarByPrefix(String var, String prefix) {

		String fullClass = varClassMap.get(var);

		if(fullClass == null)
			return "";

		Trie trie = classMethodTrieMap.get(fullClass);
		
		List<Node> nodes = trie.getNodesByPrefix(prefix);
        StringBuilder methodsSB = new StringBuilder();

        for (Node node : nodes) {

            methodsSB.append(var).append(".").append(node.getKey()).append(",");

        }

        String commaMethods = methodsSB.toString();
        int lastComma = commaMethods.lastIndexOf(",");

        if (lastComma > -1) {
            commaMethods = commaMethods.substring(0, lastComma);
        }
        return commaMethods;



	}

}
