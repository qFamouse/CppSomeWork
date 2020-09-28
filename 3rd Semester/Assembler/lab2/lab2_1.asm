.486
.model flat, stdcall
option casemap :none ; чувствительность к регистру букв в идентификаторах
include windows.inc
include kernel32.inc
includelib kernel32.lib

.data         
	buffer db 52 dup ('+')
.data?
       	
       	
	cin dd ?
	cout dd ?
	numberOfChars dd ?
.code
entryPoint:
	push STD_OUTPUT_HANDLE    ; передача параметра в функцию. Описана в windows.inc (нектр число)
	call GetStdHandle           ; вызов системной функции. Запрашивает дескриптор потока вывода	            
	mov cout, EAX        ; сохранение результата функции. Из EAX в cout
	
   	push STD_INPUT_HANDLE
	call GetStdHandle
	mov cin, EAX 
;------------------------------------------------------------------------------------------------------	
	; Ввод
  	push NULL
  	push offset numberOfChars
  	push 50
  	push offset buffer + 1
  	push cin 
  	call ReadConsole
  	;Редактирование
       	mov ESI, numberOfChars ; Кол-во записанных символов
    	mov EBX, offset buffer ; Берем адресс буффера
     	mov byte ptr [ EBX ], '{'
 	mov byte ptr [ EBX + ESI - 1 ], '}'
	;Вывод
	push NULL                 ; 5-ый параметр функции, системная константа, парамент зарезервирован, НИГДЕ не используется, возможно добавят в новых версиях
	push offset numberOfChars ; 4-ый параметр функции, адрес переменной куда ф-я передаст кол-во символов, сколько ей удалось вывести
	push ESI      ; 3-ий параметр функции, целочисленная константа. Сколько символов выводить(явно указываем)
	push offset buffer     ; 2-ой параметр функций, адрес массива символов. Адрес где храним текст
	push cout              	  ; 1-ый параметр функции, дескриптор системного объекта. Куда мы выводим
	call WriteConsole
	
	push 0 ; аналогично плюсам return 0
	call ExitProcess ; Для корректного завершения программы

end entryPoint
