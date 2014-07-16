#include<stdio.h>
#include<string.h>

int k, m, n;
int vis[555];
int bsg[555];
int map[555][555];

int find(int g) {
	for(int i = 1; i <= n; i++) {
		if(map[g][i] == 1 && vis[i] == 0) {
			vis[i] = 1;
			if(bsg[i] == 0 || find(bsg[i])) {
				bsg[i] = g;
				return 1;
			}
		}
	}
	return 0;
}

int main() {
	while(scanf("%d", &k), k != 0) {
		scanf("%d %d", &m, &n);	
		memset(map, 0, sizeof(map));
		int boy, girl;
		for(int i = 0; i < k; i++) {
			scanf("%d %d", &girl, &boy);
			map[girl][boy] = 1;
		}

		memset(bsg, 0, sizeof(bsg));

		int sum = 0;
		for(int i = 1; i <= m; i++) {
			memset(vis, 0, sizeof(vis));
			sum += find(i);
		}
		printf("%d\n", sum);
	}
	return 0;
}

