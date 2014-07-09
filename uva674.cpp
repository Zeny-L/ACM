#include<stdio.h>
#include<string.h>

int cent[5] = {1, 5, 10, 25, 50};

int main() {
	long long dp[8000] = {1};

	for(int i = 0; i < 5; i++) {
		for(int j = 0; j < 8000-100; j++) {
			dp[j + cent[i]] += dp[j];
		}
	}
	int n;
	while(scanf("%d", &n) != EOF) {
		printf("%lld\n", dp[n]);
	}
	return 0;
}
