#include<stdio.h>
#include<string.h>

char Square[105][105];

int main() {
	int T;
	scanf("%d", &T);
	int cas = 0;
	while(T--) {

		int n;
		scanf("%d", &n);
		getchar();
		memset(Square, 0, sizeof(Square));
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				scanf("%c", &Square[i][j]);
			}
			getchar();
		}

		for(int j = 1; j <= n; j++) {
			for(int i = 1; i <= n; i++) {
				for(int k = 'A'; k <= 'Z'; k++) {
					if(Square[i][j] == '.' && k != Square[i-1][j] && k!= Square[i][j-1] && k != Square[i+1][j] && k != Square[i][j+1]) {
						Square[i][j] = k;
						break;
					}
				}
			}
		}

		printf("Case %d:\n", ++cas);
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				printf("%c", Square[i][j]);
			}
			printf("\n");
		}
	}
	return 0;
}
