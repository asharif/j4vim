setlocal foldmethod=indent
set completeopt=longest,menuone
inoremap <Enter> <C-R>=SetCode()<CR><Enter>

function! SetCode()

	let code = join(getline(1,'$'), " ")
	let result = system("java -jar ~/.vim/j4vim.jar -code '" .  code . "'")
	return ""
endfunction

