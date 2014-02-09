function! javacomplete#Complete(findstart, base)
    "findstart = 1 when we need to get the text length
    if a:findstart == 1
        let line = getline('.')
        let idx = col('.')
        while idx > 0
            let idx -= 1
            let c = line[idx]
            if c =~ '\w'
                continue
            elseif ! c =~ '\.'
                let idx = -1
                break
            else
                break
            endif
        endwhile

        return idx
    "findstart = 0 when we need to return the list of completions
    else
        "vim no longer moves the cursor upon completion... fix that
        let line = getline('.')
        let idx = col('.')
        let cword = ''
        while idx > 0
            let idx -= 1
            let c = line[idx]
            if c =~ '\w' || c =~ '\.'
                let cword = c . cword
                continue
            elseif strlen(cword) > 0 || idx == 0
                break
            endif
        endwhile
	if a:base =~ ''
		let raw_results = system("java -jar ~/.vim/j4vim.jar -prefix " .  cword)
		let raw_results_arr = split(raw_results, ",")

		let result_arr = []
		for raw_result in raw_results_arr
			call add(result_arr, raw_result[len(cword):10000])
		endfor
		return result_arr
	else

		"return [a:base, cword] //a:base is class cword is method
	endif
    endif
endfunction
