function! javacomplete#Complete(findstart, base)
	if a:findstart
		" locate the start of the word
		let line = getline('.')
		let start = col('.') - 1
		while start > 0 && line[start - 1] =~ '\a'
			let start -= 1
		endwhile
		return start
	else
		let raw_results = system("java -jar ~/.vim/j4vim.jar -prefix " .  a:base)
		let raw_results_arr = split(raw_results, ",")
		return raw_results_arr

	endif
endfun
set completeopt=longest,menuone
