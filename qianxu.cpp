#include<stdio.h>
#include<string.h>

int pos;

void build(int n, char *s1, char *s2, char *s) {
    if(n <= 0)
        return ;
    int p = strchr(s1, s2[n-1]) - s1;
    s[0] = s2[n-1];
    build(p, s1, s2, s+1); //左子树
    build(n-p-1, s1+p+1, s2+p, s+p+1); //右子树
}

int main() {
    char str1[50];
    char str2[50];
    while(scanf("%s%s", str1, str2) != EOF) {
        char ch, ans[50];
        int len = strlen(str1);
        pos = 0;
        build(len, str1, str2, ans);
        ans[len] = '\0';
        puts(ans);
    }
    return 0;
}
