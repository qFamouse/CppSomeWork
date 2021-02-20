#include <windows.h>
#include <iostream>

using namespace std;
// ���������� (�.�. ����������� ����� ��������) ����������:
// N - ������ �������; K - ���������� �������; 
// size - ������ "������" ������� ��� ��������� ����� �������
// Sum - ������������� ���������� ��� ����� ��������� ������� Array
unsigned N = 0, K = 0, custom_size = 0;
unsigned Sum = 0;
unsigned* Array = NULL;

CRITICAL_SECTION cs;

// �������, ����������� � �������
DWORD WINAPI SummWorker(LPVOID lpStartNumber)
{

	unsigned st = (unsigned)lpStartNumber;
	//cout << endl << "St " << st << endl;
	unsigned partSum = 0;
	for (unsigned i = custom_size * st; i < custom_size * (st + 1); i++)
		partSum += Array[i];

	EnterCriticalSection(&cs);
	Sum = Sum + partSum;
	LeaveCriticalSection(&cs);

	return 0;
}



int main()
{
	HANDLE* hThreads = NULL;

	system("chcp 1251");
	cout << "���������� ��������� �������?" << endl << ":";
	cin >> N;
	cout << "���������� ������� ���������?" << endl << ":";
	cin >> K;
	if ((N <= 0) || (K <= 0) || (K > N) || (K > 63))
	{
		cerr << "������ ����������. ����������." << endl;
		system("pause");
		return 1;
	}
	Array = new unsigned[N];
	hThreads = new HANDLE[K];
	custom_size = N / K;

	/*	for(unsigned i=0; i<N; i++) {
			cout << endl << i << "-�� ������� ������� = ";
			cin >> Array[i];
		}
	*/
	for (unsigned i = 0; i < N; i++) Array[i] = i;

	InitializeCriticalSection(&cs);

	for (unsigned i = 0; i < K; i++)
	{
		hThreads[i] = CreateThread(NULL, 0, SummWorker, (LPVOID)i, 0, NULL);
	}
	WaitForMultipleObjects(K, hThreads, TRUE, INFINITE);

	DeleteCriticalSection(&cs);

	if (N % K != 0) {
		unsigned ost = N % K;
		unsigned partSum = 0;
		for (unsigned i = N - ost; i < N; i++) {
			partSum += Array[i];
		}
		Sum = Sum + partSum;
	}

	cout << "����� ��������� ������� ����� " << Sum << endl;
	system("pause");
	return 0;
}