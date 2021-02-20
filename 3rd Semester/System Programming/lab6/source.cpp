#include <windows.h>
#include <iostream>
#include <tchar.h>

using namespace std;

HANDLE hEvent;

int main()
{
	//	Subwork #1 Create Directory
	LPCWSTR TempDirectory = L"D:\\Temp";
	if (!CreateDirectory(TempDirectory, NULL)) {
		cout << "Error create new directory: #" << GetLastError() << endl;
		system("pause");
	}
	//	Subwork #2 Copy File
	LPCWSTR ExistingFile = L"D:\\kek\\kek.info";
	LPCWSTR NewFile = L"D:\\Temp\\test.info";
	if (CopyFile(ExistingFile, NewFile, FALSE) == FALSE) {
		cout << "Error copy file: #" << GetLastError() << endl;
		system("pause");
	}
	//	Subwork #3 Open File 
	HANDLE hTestInfo;
	if ((hTestInfo = CreateFile(NewFile, GENERIC_READ, 0, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_READONLY | FILE_FLAG_OVERLAPPED, NULL)) == INVALID_HANDLE_VALUE) {
		cout << "Error create file test.info: #" << GetLastError() << endl;
		system("pause");
	}
	// Subwork #4 Read File
	short int pointer = 100;
	//SetFilePointer(hTestInfo, pointer, NULL, FILE_BEGIN);

	// Create Event
	hEvent = CreateEvent(NULL, TRUE, TRUE, L"FeEvent");

	if (hEvent == NULL) {
		cout << "Error create event" << endl;
	}
	// Overlapped
	OVERLAPPED Overlapped;
	Overlapped.Offset = pointer;
	Overlapped.hEvent = hEvent;
	Overlapped.OffsetHigh = NULL;

	// 271014
	const INT DataLenght = 126;
	TCHAR buffer[DataLenght];
	DWORD DataRead;
	if (ReadFileEx(hTestInfo, buffer, DataLenght, &Overlapped, NULL) == FALSE) {
		cout << "Error read file: #" << GetLastError() << endl;
		system("pause");
	}

	// Subwork #5 Writting to answer.txt
	HANDLE hAnswer;
	LPCWSTR AnswerDirectory = L"D:\\Temp\\answer.txt";
	if ((hAnswer = CreateFile(AnswerDirectory, GENERIC_WRITE, 0, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL)) == INVALID_HANDLE_VALUE) {
		cout << "Error create file answer.txt: #" << GetLastError() << endl;
		system("pause");
	}

	WaitForSingleObject(hTestInfo, INFINITE);

	if (GetOverlappedResult(hTestInfo, &Overlapped, &DataRead, TRUE) == FALSE) {
		cout << "Error read file: #" << GetLastError() << endl;
		system("pause");
	}
	cout << "We read: " << DataRead << endl;

	DWORD DataWrite;
	if (WriteFile(hAnswer, buffer, DataRead, &DataWrite, NULL) == FALSE) {
		cout << "Error write file: #" << GetLastError() << endl;
		system("pause");
	}
	cout << "We write: " << DataWrite << endl;
	CloseHandle(hTestInfo);
	CloseHandle(hAnswer);

	system("pause");
	return 0;
}