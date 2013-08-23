#include<stdio.h>
#include<string.h>

static int j;
int k = 0;

void fun1(void) {
    static int i = 0;
    i++;
}

void fun2(void) {
    j = 0;
    j++;
}

int main() {
    for(int k = 0; k < 10; ++k) {
        fun1();
        fun2();
    }
    printf("%d\n", j);
    return 0;
}
