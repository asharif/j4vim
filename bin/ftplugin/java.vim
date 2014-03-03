setlocal foldmethod=indent
set completeopt=longest,menuone
inoremap <Enter> <C-R>=SetCode()<CR><Enter>


function! InitJ4Vim()

	let found = 0
	let ls =  system("ls")
	let ls_arr = split(ls, "\n")

	"attempt to kill the server first
	echom "attempting to kill server first"
	let result = system("java -jar ~/.vim/j4vim.jar -kill ")

	echom "starting server"

	"if there is a pom.xml feed classpath from maven project into j4vim
	"server.  if there is not then just use jdk classes only
	if index(ls_arr, "pom.xml") == 1

		let cp = system("mvn dependency:build-classpath | grep -A1 'Dependencies classpath:' | grep -v 'Dependencies classpath:'")
		let result = system("java -jar ~/.vim/j4vim.jar -s -cp '" .  cp . "' &")
		echom result	
	else

		let result = system("java -jar ~/.vim/j4vim.jar -s &")
		echom result	

	endif


endfunction

function! SetCode()

	let code = join(getline(1,'$'), " ")
	let result = system("java -jar ~/.vim/j4vim.jar -code " . shellescape(expand('%:p')))
	return ""
endfunction

