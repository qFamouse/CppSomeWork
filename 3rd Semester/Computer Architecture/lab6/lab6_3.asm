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
	condition db "y = {sqrt((-%s * x) + %s), x < 1", 13, 10, "{%s * ln(x), x >= 1", 13, 10, 0 
	
	OutputMSG db "| %4.6s | %4.6s |", 13, 10, 0
	                       
	kek1 db "kek1", 13, 10, 0
        kek db "kek", 13, 10, 0
	  
	one dq 1
	
	inputMSG db "Intup x1, x2, ", 01EH, "x, a, b", 13, 10, 0

.data?
	ListX db 1000 dup (?)
	ListY db 1000 dup (?)

        StrX1 db 100 dup (?)
        StrX2 db 100 dup (?)
        StrStepX db 100 dup (?)
        StrA db 100 dup (?)
        StrB db 100 dup (?)	
	
        NumberX1 dq ?
        NumberX2 dq ?
        NumberStepX dq ?
        NumberA dq ?
        NumberB dq ?
        
        dqbuf dq ?
        dqbuf2 dq ?
        
        x dd ?
        y dd ?
        
        buffer db 100 dup (?)
        buf dd ?
        ddbuffer dd 100 dup (?)
        answer db 1000 dup (?)
        
	numberOfCharsWritten dd ?
       	cin dd ?
	cout dd ?
.code
Calculate:     
    ;	mov EAX, NumberX1 ; Начальная точка счета
	mov ESI, 0
	FLD NumberX1
CalculateStart:
	FCOM NumberX2
	FSTSW AX
    	SAHF
    	jae CalculateFinish ; Если x1 >= x2 то завершаем
          
     
     
     
      
      
    	FCOM one
    	FSTSW AX
    	SAHF
    	jae XGreaterOrEqualOne ; Смотрим как проводить вычисления
    	
 ;Вычисления 1    
 	FSTP dqbuf ; закидываем x в буф. переменную         
 
 	FLD NumberA ; Пушим А
   	FCHS ; Обратный модуль
   	FMUL dqbuf ; домножаем на X
   	FADD NumberA ; прибавляем А
   	FSQRT
   	FSTP dqbuf2
	
	jmp AfterCreateResult
	                         
  ;	cmp EAX, NumberX2 ; Сравниваем X1 и X2 (границы)
   ;	jnl CalculateFinish

    ;	cmp EAX, 1
     ;	jnl XGreaterOrEqualOne ; Смотрим как проводить вычисления

;Вычисления
     ;	FLD NumberA ; Пушим А
   ;	FCHS ; Обратный модуль
   ;	FIMUL EAX
   ;	FIADD NumberA
   ;	FSQRT
   ;	FSTP EDX
  ;	
   ;	jmp AfterCreateResult

XGreaterOrEqualOne: ; Если x >= 1
        	FSTP dqbuf ; закидываем x в буф. переменную         

   	FLD NumberB ; St(0) = B
      	FLD dqbuf ;  ; St(0) = X ; St(1) = B
       	FYL2X ; St(0) = St(1) * log2(St(0)) => B * log2(X)
    	fldln2 ; St(0) = ln(2) | St(1) = B * log2(X)
    	fmul ; St(0) = B * ln(X)          
        FSTP dqbuf2


AfterCreateResult:
	push offset ddbuffer
	push dword ptr dqbuf2 + 4
	push dword ptr dqbuf2
	call FloatToStr

    	mov EBX, offset ListY 
    	mov EAX, ddbuffer
      	mov dword ptr [ EBX + ESI * 4 ], EAX	
        ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
        push offset ddbuffer
	push dword ptr dqbuf + 4
	push dword ptr dqbuf
	call FloatToStr

    	mov EBX, offset ListX
    	mov EAX, ddbuffer
      	mov dword ptr [ EBX + ESI * 4 ], EAX
       
    	inc ESI
    	
   	FLD dqbuf ; Возвращаем X
   	FADD NumberStepX ; Добавляем шаг

   	jmp CalculateStart ; Продолжаем цикл

CalculateFinish:     
ret 

Conclusion:
	mov buf, ESI
	mov ESI, 0
	mov EAX, 0
ConclusionStart:
	cmp ESI, buf
	jnl ConclusionFinish 
	; Подготовка текста

	
	
	mov EBX, offset ListY 
	
	mov EAX, dword ptr [ EBX + ESI * 4 ]
    	mov y, EAX
	;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	mov EBX, offset ListX
	
	mov EAX, dword ptr [ EBX + ESI * 4 ]
    	mov x, EAX

 	push offset y
     	push offset x
     	push offset OutputMSG
     	push offset answer
     	call wsprintf
        ;Вывот отформатированного текста
        push offset answer
	call lstrlen
	
	push NULL
	push offset numberOfCharsWritten
	push EAX
	push offset answer
	push cout
	call WriteConsole
	
	inc ESI
	jmp ConclusionStart
ConclusionFinish:
ret

 


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
;Ввод числа X1
	push NULL
	push offset numberOfCharsWritten
	push 1000
	push offset StrX1
	push cin
	call ReadConsole
	
    	mov EDX, offset StrX1
    	mov EAX, numberOfCharsWritten
    	mov byte ptr [ EDX + EAX - 2 ], 0
    	
    	push offset NumberX1 
    	push offset StrX1
    	call StrToFloat
;Ввод числа X2
	push NULL
	push offset numberOfCharsWritten
	push 1000
	push offset StrX2
	push cin
	call ReadConsole 
	
    	mov EDX, offset StrX2
    	mov EAX, numberOfCharsWritten
    	mov byte ptr [ EDX + EAX - 2 ], 0
    	
    	push offset NumberX2 
    	push offset StrX2
    	call StrToFloat
;Ввод числа StepX
 	push NULL
	push offset numberOfCharsWritten
	push 1000
	push offset StrStepX 
	push cin
	call ReadConsole 
	
    	mov EDX, offset StrStepX 
    	mov EAX, numberOfCharsWritten
    	mov byte ptr [ EDX + EAX - 2 ], 0
    	
    	push offset NumberStepX 
    	push offset StrStepX 
    	call StrToFloat
;Ввод числа A 
	push NULL
	push offset numberOfCharsWritten
	push 1000
	push offset StrA 
	push cin
	call ReadConsole 
	
    	mov EDX, offset StrA 
    	mov EAX, numberOfCharsWritten
    	mov byte ptr [ EDX + EAX - 2 ], 0
    	
    	push offset NumberA 
    	push offset StrA 
    	call StrToFloat  
;Ввод числа B
	push NULL
	push offset numberOfCharsWritten
	push 1000
	push offset StrB 
	push cin
	call ReadConsole 
	
    	mov EDX, offset StrB 
    	mov EAX, numberOfCharsWritten
    	mov byte ptr [ EDX + EAX - 2 ], 0
    	
    	push offset NumberB 
    	push offset StrB 
    	call StrToFloat  
    	
    	
;------------------------------------------------------------------------------------------------------ Основной код
call Calculate
call Conclusion                 

; Задержка закрытия окна 
;------------------------------------------------------------------------------------------------------
;Выход из программы	
	push 0
	call ExitProcess
END entryPoint
