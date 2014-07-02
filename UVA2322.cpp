#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<algorithm>

using namespace std;

struct Point{
    int l;
    int w;
    int mark;
}point[5555];

int cmp(Point a, Point b) {
    if(a.l != b.l)
        return a.l < b.l;
    return a.w <= b.w;
}

int main() {
    int T;
    scanf("%d", &T);

    while(T--) {
        int n;
        scanf("%d", &n);

        int time = 0;
        memset(point, 0, sizeof(point));
        for(int i = 0; i < n; i++)
            scanf("%d %d", &point[i].l, &point[i].w);

        sort(point, point+n, cmp);

        int tmp;
        for(int i = 0; i < n; i++) {
            if(point[i].mark)
                continue;
            time++;
            tmp = point[i].w;
            for(int j = i; j < n; j++) {
                if(point[j].w >= tmp && point[j].mark == 0) {
                    tmp = point[j].w;
                    point[j].mark = 1;
                }
            }
        }
        printf("%d\n", time);
    }
    return 0;
}
