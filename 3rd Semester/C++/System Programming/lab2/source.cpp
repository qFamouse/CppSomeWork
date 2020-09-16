#include <windows.h>
#include <iostream>

#define Task 6

using namespace std;

int main() {

#if Task == 1 // Task 1 // // // // // // // // // // // // // // // // // // 
	printf("Task #1\n");

	// Creating StartUp and ProcInfo \ Crearning memory
	STARTUPINFO StartUP = { 0 };
	ZeroMemory(&StartUP, sizeof(&StartUP));
	StartUP.cb = sizeof(StartUP);
	PROCESS_INFORMATION ProcInfo;

	if (!(CreateProcess(L"C:\\Windows\\System32\\calc.exe", NULL, NULL, NULL, FALSE, NULL, NULL, NULL, &StartUP, &ProcInfo))) {
		cout << "Error creating the process!" << endl;
	}

	// Wait until child process exits | Solution solution for to avoid leak memory.
	WaitForSingleObject(ProcInfo.hProcess, INFINITE);
	CloseHandle(ProcInfo.hProcess);
	CloseHandle(ProcInfo.hThread);
#elif Task == 2 // Task 2 // // // // // // // // // // // // // // // // // // 
	printf("Task #2\n");

	// Creating StartUp and ProcInfo \ Crearning memory
	STARTUPINFO StartUP = { 0 };
	ZeroMemory(&StartUP, sizeof(&StartUP));
	StartUP.cb = sizeof(StartUP);
	PROCESS_INFORMATION ProcInfo;

	// Enter way from keyboard
	WCHAR* WayToFile = new WCHAR[100];
	cout << "Write way" << endl;
	wcin >> WayToFile;

	if (!(CreateProcess(WayToFile, NULL, NULL, NULL, FALSE, NULL, NULL, NULL, &StartUP, &ProcInfo))) {
		cout << "Error creating the process!" << endl;
	}

	// Wait until child process exits | Solution solution for to avoid leak memory.
	WaitForSingleObject(ProcInfo.hProcess, INFINITE);
	CloseHandle(ProcInfo.hProcess);
	CloseHandle(ProcInfo.hThread);
#elif Task == 3	// Task 3 // // // // // // // // // // // // // // // // // // 
	printf("Task #3\n");

	if (!(ShellExecute(NULL, L"open", L"C:\\Windows\\System32\\notepad.exe", L"G:\\file.txt", NULL, 1))) {
		cout << "Error opening file!" << endl;
	}

#elif Task == 4	// Task 4 // // // // // // // // // // // // // // // // // // 
	printf("Task #4\n");

	SHELLEXECUTEINFO OpenTempInfo = {sizeof(SHELLEXECUTEINFO), NULL, NULL, NULL, L"C:\\Windows\\Temp", NULL, NULL, 1, NULL };

	//OpenTempInfo.cbSize = sizeof(SHELLEXECUTEINFO);
	//OpenTempInfo.fMask = NULL;
	//OpenTempInfo.hwnd = NULL;
	//OpenTempInfo.lpVerb = NULL;
	//OpenTempInfo.lpFile = L"E:\\Torrent Files";
	//OpenTempInfo.lpParameters = NULL;
	//OpenTempInfo.lpDirectory = NULL;
	//OpenTempInfo.nShow = 1;
	//OpenTempInfo.hInstApp = NULL;


	 //ShellExecute(OpenTempInfo.hwnd, L"open", L"C:\\Windows\\Temp", NULL, NULL, 1);

	 if (!(ShellExecuteEx(&OpenTempInfo))) {
		 cout << "Error opening: #" << GetLastError() << endl;
	}
	
#elif Task == 5	// Task 4 // // // // // // // // // // // // // // // // // // 
	printf("Task #5\n");

	cout << "Console ProcessID: " << GetCurrentProcessId() << endl;
	// Enter data
	INT ProcessID;
	cout << "Enter the process ID: ";
	wcin >> ProcessID;



	HANDLE processHandle = OpenProcess(PROCESS_TERMINATE, TRUE, ProcessID);

	if (!(TerminateProcess(processHandle, 1))) {
		cout << "Error closing process" << endl;
	}

	cout << "Closing Console.." << endl;
	ExitProcess(1);

#elif Task == 6	// Task 4 // // // // // // // // // // // // // // // // // // 
	printf("Task #6\n");

	cout << "Console ProcessID: " << GetCurrentProcessId() << endl;
	// Enter data
	INT ProcessID;
	cout << "Enter the process ID: ";
	wcin >> ProcessID;

	HANDLE processHandle = OpenProcess(PROCESS_SET_INFORMATION, TRUE, ProcessID);
	
	// NORMAL_PRIORITY_CLASS
	// BELOW_NORMAL_PRIORITY_CLASS
	// ABOVE_NORMAL_PRIORITY_CLASS
	// HIGH_PRIORITY_CLASS
	// REALTIME_PRIORITY_CLASS
	// IDLE_PRIORITY_CLASS
	if (!(SetPriorityClass(processHandle, HIGH_PRIORITY_CLASS))) {
		cout << "Error changing priority class" << endl;
	}

#endif

	system("pause");
	return 0;
}