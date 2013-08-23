#include<stdio.h>
#include<string.h>

void build(int n, char *s1, char *s2, char *s) {
    if(n <= 0)
        return;
    int p = strchr(s2, s1[0]) - s2;
    build(p, s1+1, s2, s);
    build(n-1-p, s1+p+1, s2+p+1, s+p);
    s[n-1] = s1[0];
}

int main() {
    char str1[30], str2[30];
    char ans[30];
    int len;
    while(scanf("%s%s", str1, str2) != EOF) {
        len = strlen(str1);
        build(len, str1, str2, ans);
        ans[len] = '\0';
        puts(ans);
    }
    return 0;
}
