.486
.model flat, stdcall
option casemap :none ; чувствительность к регистру букв в идентификаторах
include windows.inc
include kernel32.inc
include masm32.inc 
include user32.inc                     

includelib user32.lib
includelib kernel32.lib
includelib masm32.lib

.data   
	condition db "A0 = 18 | d = 43", 13, 10, 0
	
	template db "%d ", 0  
	
	buff db 50 dup (0)
	
.data?  
	;CUSTOM ARRAY
	massiv db 10 dup (?)
	;BUFFER
	buffer db 25 dup (?)
	;
	numberOfCharsWritten dd ?
     	;data
        a dd ?
        d dd ?
     	      
     	ArraySum dd ?
     	
	cin dd ?
	cout dd ?
.code
GetArraySum:
	mov EBX, offset massiv
	mov ESI, 0
	mov EAX, 0
ArraySumStart:
	cmp ESI, 10
	jnl ArraySumFinish
	add EAX, [ EBX + ESI * 4 ]
	inc ESI
	jmp ArraySumStart
ArraySumFinish:
	ret
	
ArProg:
     	mov EBX, offset massiv
     	mov ESI, 0
     	mov EAX, a ; first a0
ArProgSrart:
     	cmp ESI, 10    	
     	jnl ArProgFinish
     	add EAX, d
     	mov dword ptr [ EBX + ESI * 4 ], EAX
     	inc ESI
     	jmp ArProgSrart
ArProgFinish:
     	ret
     	
arrayToStr:
        ; Стандартный пролог функции 
        push EBP
        mov EBP, ESP
        ; Начало цикла - пока есть необработанные элементы
        cycle:
                cmp dword ptr [ EBP + 16 ], 0
                je endFunction
                ; Формирование текстового представления целого числа
                mov EAX, [ EBP + 12 ]         ; указатель на очередное число
                push [ EAX ]                  ; целое число (элемента массива)
                                              ; преобразуемое в строку
                push offset template          ; шаблон строки, в который подставляются значения 
                                              
                push [ EBP + 8 ]              ; адрес буфера для размещения итоговой строки
                                              
                call wsprintf                 ; в EAX записывается число символов, записанных в буфер
                                              
                add ESP, 12                   ; выровняем стек
                ; Подготовка к следующему числу
                add [ EBP + 8 ], EAX          ; рассчитаем адрес строки для
                                              ; следующего числа
                add dword ptr [ EBP + 12 ], 4 ; перемещаем указатель на следующий
                                              ; элемент массива
                dec dword ptr [ EBP + 16 ]    ; уменьшаем счетчик
        ; ????? ?????
        jmp cycle
        endFunction:
        ; ??????????? ?????? ???????
        pop EBP
; ????? ? ????????? ????
ret 12	

entryPoint:
	push STD_OUTPUT_HANDLE    ; передача параметра в функцию. Описана в windows.inc (нектр число)
	call GetStdHandle           ; вызов системной функции. Запрашивает дескриптор потока вывода	            
	mov cout, EAX        ; сохранение результата функции. Из EAX в cout
	
    	push STD_INPUT_HANDLE
    	call GetStdHandle
   	mov cin, EAX 
   	
 newStr:

           
        push offset newline
        call lstrlen
        
        push NULL                 
    	push numberOfCharsWritten 
    	push EAX
    	push offset buff
    	push cout           
    	call WriteConsole
;------------------------------------------------------------------------------------------------------
       ;Input x
     	push NULL
    	push offset numberOfCharsWritten 
    	push 25
    	push offset buffer 
    	push cin 
    	call ReadConsole
    	
    	mov ESI, numberOfCharsWritten
    	mov EBX, offset buffer
    	mov byte ptr [ EBX + ESI - 2 ], 0
    	
    	push offset buffer
    	call atodw
    	mov a, EAX
    	;Input y
     	push NULL
    	push offset numberOfCharsWritten 
    	push 25
    	push offset buffer 
    	push cin 
    	call ReadConsole     
    	
     	mov ESI, numberOfCharsWritten
    	mov EBX, offset buffer
    	mov byte ptr [ EBX + ESI - 2 ], 0
    	
    	push offset buffer
    	call atodw
    	mov d, EAX          
;------------------------------------------------------------------------------------------------------
	call ArProg
	
        call GetArraySum
        mov ArraySum, EAX
                                                                                                       
 
	
	
	
	
;------------------------------------------------------------------------------------------------------
        push 10
        push offset massiv
        push offset buff
        call arrayToStr 
           
        push offset buff
        call lstrlen
        
        push NULL                 
    	push numberOfCharsWritten 
    	push EAX
    	push offset buff
    	push cout           
    	call WriteConsole
;------------------------------------------------------------------------------------------------------ 
        push offset buffer
        push ArraySum
        call dwtoa 
        
        push offset buffer
        call lstrlen
        
        push NULL                 
    	push numberOfCharsWritten 
    	push EAX
    	push offset buffer
    	push cout           
    	call WriteConsole
;------------------------------------------------------------------------------------------------------    		                                                                                       
	push 0 ; аналогично плюсам return 0                                                            
	call ExitProcess ; Для корректного завершения программы


end entryPoint
