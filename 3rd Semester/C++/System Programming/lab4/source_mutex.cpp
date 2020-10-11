#include <windows.h>
#include <iostream>

using namespace std;
// Глобальные (т.е. разделяемые всеми потоками) переменные:
// N - размер массива; K - количество потоков; 
// size - размер "проции" массива для обработки одним потоком
// Sum - накопительная переменная для суммы элементов массива Array
unsigned N = 0, K = 0, custom_size = 0;
unsigned Sum = 0;
unsigned* Array = NULL;

HANDLE hMytex;

// Функция, запускаемая в потоках
DWORD WINAPI SummWorker(LPVOID lpStartNumber)
{

	unsigned st = (unsigned)lpStartNumber;
	//cout << endl << "St " << st << endl;
	unsigned partSum = 0;
	for (unsigned i = custom_size * st; i < custom_size * (st + 1); i++) {
		partSum += Array[i];
	}

	if (WaitForSingleObject(hMytex, INFINITE) == WAIT_OBJECT_0) {
		Sum = Sum + partSum;
	}
	ReleaseMutex(hMytex);

	return 0;
}



int main()
{
	HANDLE* hThreads = NULL;

	system("chcp 1251");
	cout << "Количество элементов массива?" << endl << ":";
	cin >> N;
	cout << "Количество потоков программы?" << endl << ":";
	cin >> K;
	if ((N <= 0) || (K <= 0) || (K > N) || (K > 63))
	{
		cerr << "Ошибка параметров. Завершение." << endl;
		system("pause");
		return 1;
	}
	Array = new unsigned[N];
	hThreads = new HANDLE[K];
	custom_size = N / K;

	/*	for(unsigned i=0; i<N; i++) {
			cout << endl << i << "-ый элемент массива = ";
			cin >> Array[i];
		}
	*/
	for (unsigned i = 0; i < N; i++) Array[i] = i;

	hMytex = CreateMutex(NULL, FALSE, L"FeMutex");

	for (unsigned i = 0; i < K; i++)
	{
		hThreads[i] = CreateThread(NULL, 0, SummWorker, (LPVOID)i, 0, NULL);
	}
	WaitForMultipleObjects(K, hThreads, TRUE, INFINITE);

	if (N % K != 0) {
		unsigned ost = N % K;
		unsigned partSum = 0;
		for (unsigned i = N - ost; i < N; i++) {
			partSum += Array[i];
		}
		Sum = Sum + partSum;
	}

	cout << "Сумма элементов массива равна " << Sum << endl;
	system("pause");
	return 0;
}