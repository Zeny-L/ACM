#include<stdio.h>
#include<string.h>

struct Test {
    char a[5];
    char b[5];
}test[20];

class Csort {
    public:
        int operator() (Test a, int b) { 
            return b;
        }
};

int main() {
    Csort have_int;
    int num[20];
    char tmp[50];

    strcpy(tmp, "nima");

    for(int i = 0; i < 20; i++) {
        test[i].a[0] = '\0';
        test[i].b[0] = '\0';
        if(i % 2 == 0)
            num[i] = 1;
        else
            num[i] = -1;
    }

    for(int i = 0; i < 20; i++) {
        if(have_int(test[i], num[i]) > 0)
            printf("Yes\n");
        else
            printf("No\n");
    }
    return 0;
}
