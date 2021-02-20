.486
.model flat, stdcall
option casemap :none
include windows.inc
include kernel32.inc
include masm32.inc
include user32.inc
includelib kernel32.lib
includelib masm32.lib
includelib user32.lib

.data
	template db "3 * %s - (%s + %s)/2 = %s", 0
	
	inputMSG db "Intup a and b numbers", 13, 10, 0
	
	
	
	two dd 2, 0
	three dd 3, 0

.data?
        StrA db 1000 dup (?)
        StrB db 1000 dup (?)
	
        a dq ?
        b dq ?
        R dq ?
        
        buffer dd 100 dup (?)
        
        RStr db 100 dup (?)
        answer db 100 dup (?)
        
	numberOfCharsWritten dd ?
       	cin dd ?
	cout dd ?
.code


entryPoint:
; Дескрипторы ввода/вывода
	push STD_INPUT_HANDLE
	call GetStdHandle
	mov cin, EAX
	push STD_OUTPUT_HANDLE
	call GetStdHandle
	mov cout, EAX
;Вывод сообщения с просьбой ввода данных
	push offset inputMSG 
	call lstrlen
	
	push NULL
	push offset numberOfCharsWritten
	push EAX
	push offset inputMSG
	push cout
	call WriteConsole
;Ввод числа a
	push NULL
	push offset numberOfCharsWritten
	push 1000
	push offset StrA
	push cin
	call ReadConsole
	
    	mov EDX, offset StrA
    	mov EAX, numberOfCharsWritten
    	mov byte ptr [ EDX + EAX - 2 ], 0
    	
    	push offset a
    	push offset StrA
    	call StrToFloat
;Ввод числа b
	push NULL
	push offset numberOfCharsWritten
	push 1000
	push offset StrB
	push cin
	call ReadConsole 
	
    	mov EDX, offset StrB
    	mov EAX, numberOfCharsWritten
    	mov byte ptr [ EDX + EAX - 2 ], 0
    	
    	push offset b
    	push offset StrB
    	call StrToFloat
;Вычисление с помощью сопроцессора
   	FINIT
     	FLD a
   	FIMUL three
	
     	FLD a
     	FADD b
     	FIDIV two
		
        FSUBR ST, ST(1)
        FSTP R 
;Преобразование числа в строку
      	push offset RStr
      	push dword ptr R + 4
      	push dword ptr R
      	call FloatToStr
; Формирование строки результата для вывода	
	push offset RStr
	push offset StrB
	push offset StrA
	push offset StrA
	push offset template
	push offset answer
	call wsprintf
; Вывод строки-результата
	push offset answer
	call lstrlen
	
	push NULL
	push offset numberOfCharsWritten
	push EAX
	push offset answer
	push cout
	call WriteConsole
; Задержка закрытия окна
	push NULL
	push offset numberOfCharsWritten
	push 1
	push offset RStr
	push cin
	call ReadConsole

;Выход из программы	
	push 0
	call ExitProcess
END entryPoint