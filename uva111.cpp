#include<stdio.h>
#include<string.h>

int max(int a, int b) {
	if(a > b)
		return a;
	else
		return b;
}

int main() {
	int l;
	int t;
	int num1[100];
	
	scanf("%d", &l);
	for(int i = 1; i <= l; i++) {
		scanf("%d", &t);
		num1[t] = i;
	}
	
	int num2[100];
	while(scanf("%d", &t) != EOF) {
		num2[t] = 1;
		for(int i = 2; i <= l; i++) {
			scanf("%d", &t);
			num2[t] = i;
		}

		int dp[100][100];
		memset(dp, 0, sizeof(dp));

		for(int i = 1; i <= l; i++) {
			for(int j = 1; j <= l; j++) {
				if(num1[i] == num2[j])
					dp[i][j] = dp[i-1][j-1] + 1;
				else
					dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
			}
		}

		printf("%d\n", dp[l][l]);
	}

	return 0;
}
