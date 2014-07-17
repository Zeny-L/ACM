#include<stdio.h>
#include<string.h>

int k, t;
int map[52][52];
int vis[52];
int has[52];

const char pai[52][3] = {
	"2C", "2D", "2S", "2H",
	"3C", "3D", "3S", "3H",
	"4C", "4D", "4S", "4H",
	"5C", "5D", "5S", "5H",
	"6C", "6D", "6S", "6H",
	"7C", "7D", "7S", "7H",
	"8C", "8D", "8S", "8H",
	"9C", "9D", "9S", "9H",
	"TC", "TD", "TS", "TH",
	"JC", "JD", "JS", "JH",
	"QC", "QD", "QS", "QH",
	"KC", "KD", "KS", "KH",
	"AC", "AD", "AS", "AH"};

int getNum(char *str) {
	for(int i = 0; i < 52; i++) {
		if(strcmp(pai[i], str) == 0)
			return i;
	}
	return -2;
}

int find(int a) {
	for(int i = 0; i < 52; i++) {
		if(map[a][i] && vis[i] == 0) {
			vis[i] = 1;
			if(has[i] == -1 || find(has[i])) {
				has[i] = a;
				return 1;
			}
		}
	}
	return 0;
}

int main() {
	scanf("%d", &t);
	while(t--) {
		scanf("%d", &k);

		char adam[26][3];
		char eve[26][3];

		for(int i = 0; i < k; i++)
			scanf("%s", adam[i]);
		for(int i = 0; i < k; i++)
			scanf("%s", eve[i]);

		memset(map, 0, sizeof(map));

		for(int i = 0; i < k; i++) {
			for(int j = 0; j < k; j++) {
				int p1 = getNum(adam[i]);
				int p2 = getNum(eve[j]);
				if(p1 < p2)
					map[p1][p2] = 1;
			}
		}

		memset(has, -1, sizeof(has));

		int sum = 0;
		for(int i = 0; i < 52; i++) {
			memset(vis, 0, sizeof(vis));
			sum += find(i);
		}
		printf("%d\n", sum);
	}
}
