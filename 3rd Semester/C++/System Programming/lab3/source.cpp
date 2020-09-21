#include <Windows.h>
#include <iostream>
#include <string>
#include <algorithm>


#define Task 3

#define ArrSize 30

using namespace std;

DWORD WINAPI InfinityFunk(LPVOID lp) {
    for (int i = 0;; i++);
    return 0;
}

void BeepControl(HANDLE hTread) {
    //if (GetThreadId(hTread) != 0) {
    //    cout << "alive" << endl;
    //}
    //else {
    //    cout << "no alive" << endl;
    //}

    string command_reg;
    string command; // without register
    cout << "-[ Music Control Panel ]-" << endl;
    cout << "Command list:" << endl;
    cout << "start - Play/Resume Music" << endl;
    cout << "pause - Pause Music" << endl;
    cout << "stop - Stop Music" << endl;
    cout << "close - Close Control Panel" << endl << endl;
    cout << "-- ";
    cin >> command_reg;

    command.resize(command_reg.size());
    transform(command_reg.begin(), command_reg.end(), command.begin(), ::tolower);

    if (command == "start") {
        if (ResumeThread(hTread) == -1) {
            cout << "Error with ResumeThread! Error Code: " << GetLastError() << endl;
            system("pause");
        }
        system("cls");
        BeepControl(hTread);
    }
    else if (command == "pause") {
        if (SuspendThread(hTread) == -1) {
            cout << "Error with SuspendThread! Error Code: " << GetLastError() << endl;
            system("pause");
        }
        system("cls");
        BeepControl(hTread);
    }
    else if (command == "stop") {
        if (!(TerminateThread(hTread, 0))) {
            cout << "Error with TerminateThread! Error Code: " << GetLastError() << endl;
            system("pause");
        }
    }
    else if (command == "close") {

    }
    else {
        cout << "Unknown command " << command << endl;
        BeepControl(hTread);
    }
}

DWORD WINAPI BeepMusic(LPVOID lp) {
    Beep(1480, 200);
    Beep(1568, 200);
    Beep(1568, 200);
    Beep(1568, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(369.99, 200);
    Beep(392, 200);
    Beep(369.99, 200);
    Beep(392, 200);
    Beep(392, 400);
    Beep(196, 400);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(880, 200);
    Beep(830.61, 200);
    Beep(880, 200);
    Beep(987.77, 400);
    Beep(880, 200);
    Beep(783.99, 200);
    Beep(698.46, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(880, 200);
    Beep(830.61, 200);
    Beep(880, 200);
    Beep(987.77, 400);
    Sleep(200);
    Beep(1108, 10);
    Beep(1174.7, 200);
    Beep(1480, 10);
    Beep(1568, 200);
    Sleep(200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(783.99, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(880, 200);
    Beep(830.61, 200);
    Beep(880, 200);
    Beep(987.77, 400);
    Beep(880, 200);
    Beep(783.99, 200);
    Beep(698.46, 200);
    Beep(659.25, 200);
    Beep(698.46, 200);
    Beep(784, 200);
    Beep(880, 400);
    Beep(784, 200);
    Beep(698.46, 200);
    Beep(659.25, 200);
    Beep(587.33, 200);
    Beep(659.25, 200);
    Beep(698.46, 200);
    Beep(784, 400);
    Beep(698.46, 200);
    Beep(659.25, 200);
    Beep(587.33, 200);
    Beep(523.25, 200);
    Beep(587.33, 200);
    Beep(659.25, 200);
    Beep(698.46, 400);
    Beep(659.25, 200);
    Beep(587.33, 200);
    Beep(493.88, 200);
    Beep(523.25, 200);
    Sleep(400);
    Beep(349.23, 400);
    Beep(392, 200);
    Beep(329.63, 200);
    Beep(523.25, 200);
    Beep(493.88, 200);
    Beep(466.16, 200);
    Beep(440, 200);
    Beep(493.88, 200);
    Beep(523.25, 200);
    Beep(880, 200);
    Beep(493.88, 200);
    Beep(880, 200);
    Beep(1760, 200);
    Beep(440, 200);
    Beep(392, 200);
    Beep(440, 200);
    Beep(493.88, 200);
    Beep(783.99, 200);
    Beep(440, 200);
    Beep(783.99, 200);
    Beep(1568, 200);
    Beep(392, 200);
    Beep(349.23, 200);
    Beep(392, 200);
    Beep(440, 200);
    Beep(698.46, 200);
    Beep(415.2, 200);
    Beep(698.46, 200);
    Beep(1396.92, 200);
    Beep(349.23, 200);
    Beep(329.63, 200);
    Beep(311.13, 200);
    Beep(329.63, 200);
    Beep(659.25, 200);
    Beep(698.46, 400);
    Beep(783.99, 400);
    Beep(440, 200);
    Beep(493.88, 200);
    Beep(523.25, 200);
    Beep(880, 200);
    Beep(493.88, 200);
    Beep(880, 200);
    Beep(1760, 200);
    Beep(440, 200);
    Beep(392, 200);
    Beep(440, 200);
    Beep(493.88, 200);
    Beep(783.99, 200);
    Beep(440, 200);
    Beep(783.99, 200);
    Beep(1568, 200);
    Beep(392, 200);
    Beep(349.23, 200);
    Beep(392, 200);
    Beep(440, 200);
    Beep(698.46, 200);
    Beep(659.25, 200);
    Beep(698.46, 200);
    Beep(739.99, 200);
    Beep(783.99, 200);
    Beep(392, 200);
    Beep(392, 200);
    Beep(392, 200);
    Beep(392, 200);
    Beep(196, 200);
    Beep(196, 200);
    Beep(196, 200);
    Beep(185, 200);
    Beep(196, 200);
    Beep(185, 200);
    Beep(196, 200);
    Beep(207.65, 200);
    Beep(220, 200);
    Beep(233.08, 200);
    Beep(246.94, 200);

    return 0;
}

DWORD WINAPI Task3OddThread(LPVOID lp) {
    for (int i = 1; i <= ArrSize; i++) {
        if (i % 2 != 0) {
            cout << i << " ";
        }
    }
    return 0;
}

DWORD WINAPI Task3EvenThread(LPVOID lp) {
    for (int i = 1; i <= ArrSize; i++) {
        if (i % 2 == 0) {
            cout << i << " ";
        }
    }
    return 0;
}

int main() {
#if Task == 0

#elif Task == 1
    // Create Threads
    DWORD FerstThreadID;
    HANDLE FirstThread = CreateThread(NULL, 0, InfinityFunk, NULL, 0, &FerstThreadID);
    DWORD SecondThreadID;
    HANDLE SecondThread = CreateThread(NULL, 0, InfinityFunk, NULL, 0, &SecondThreadID);
    // Check work
    if (FirstThread == NULL || SecondThread == NULL) {
        MessageBox(NULL, L"First or Second Thread does not work", L"Warning!", MB_OK);
    }

    system("pause");
    if (!(TerminateThread(FirstThread, 0))) {
        MessageBox(NULL, L"Error with terminate FirstThread", L"Warning!", MB_OK);
        system("pause");
    }
    else if (!(TerminateThread(SecondThread, 0))) {
        MessageBox(NULL, L"Error with terminate SecondThread", L"Warning!", MB_OK);
        system("pause");
    }
    else {
        cout << "All Threads will be closed!" << endl;
    }
#elif Task == 2
    DWORD MusicID;
    HANDLE Music = CreateThread(NULL, 0, BeepMusic, NULL, CREATE_SUSPENDED, &MusicID);

    BeepControl(Music);

#elif Task == 3
    //// Create Array
    //int const arr_size = 30;
    //int arr[arr_size];
    //for (int i = 0; i < arr_size; i++) {
    //    arr[i] = i+1;
    //}
    //cout << "Our Array" << endl;
    //for (int i = 0; i < arr_size; i++) {
    //    cout << arr[i] << " ";
    //}
    //cout << endl << endl << endl;

    // Create Threads
    DWORD FerstThreadID;
    HANDLE FirstThread = CreateThread(NULL, 0, Task3EvenThread, NULL, 0, &FerstThreadID);
    DWORD SecondThreadID;
    HANDLE SecondThread = CreateThread(NULL, 0, Task3OddThread, NULL, 0, &SecondThreadID);


#endif
    system("pause");
    return 0;
}