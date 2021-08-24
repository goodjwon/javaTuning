* * *
## [컴퓨터 공부방/알고리즘]

### [미로찾기 알고리즘 (재귀함수 응용문제) JAVA](/7)

출처: 다이제 Digest 2019\. 9. 10. 02:30 (https://digest1.tistory.com/7)

### 문제 확인

![](https://blog.kakaocdn.net/dn/UdJaj/btqx9Ag3a2P/v74KH5K6kb3aRKeSBU1da1/img.png)

``` java


    public class Maze{
    	private static int N = 8;
    	private static int[][] maze= {
    			{0,0,0,0,0,0,0,1},
    			{0,1,1,1,0,1,0,1},
    			{0,1,0,1,0,0,0,1},
    			{0,0,0,1,1,1,0,0},
    			{1,0,1,1,0,0,1,1},
    			{0,0,0,0,0,1,0,1},
    			{1,0,1,1,0,0,0,1},
    			{0,0,1,1,1,1,0,0}
    	};
        public static boolean findMazePath(int x, int y) {
            //TODO: 구현부
        }
    	public static void printMaze() {
            // 2차원 배열 출력 메서드
    		for (int i = 0; i < maze.length; i++) {
    			for (int j = 0; j < maze[i].length; j++) {
    				System.out.print(maze[i][j] + " ");
    			}
    			System.out.println("");
    		}
    		System.out.println("");
    	}
        public static void main(String[] args) {
    		printMaze();
    		findMazePath(0,0);
    		printMaze();
    	}
    }
```
### 접근 방법

* * *

일단 메서드에서 findMazePath(0,0)을 주고 시작하는 것을 보면 x와 y좌표 0,0에서 시작한다는 사실은 알 수 있다.

#### 사람이 푸는 방식

연필로 한 칸 한 칸 긋고 막히는 부분이 있으면 경로를 지우고 새로운 경로를 탐색하지 않을까?

**\[경로를 찾을 때까지 반복되는 작업\]**

    1.  방향을 정해서 뚫린 곳부터 일단 탐색한다.
    
    2.  부분에 도달했다면? (X축이나 Y축의 끝이면 갈 데가 없으니까 여기에 도달했다면?)
    
    3. 막힌 것이므로 되도랑간다.
    
    4. X축과 Y축이 모두 끝부분이라면 출구이므로 도착했으므로 프로그램을 종료한다.

#### 기계가 푸는 방식

문제에서는 배열의 **0은 지나다닐 수 있는 통로고 1은 벽**이라고 했다.

그런데 탐색을 할 때마다 막힌 경로인지 혹은 탐색 중인지를 표시해두는 것이 좋겠다.

그런 것들을 표시하지 않는다면 분명 다른 위치에서 이미 탐색해서 온 경로를 또 다시 재귀호출하여 무한루프에 빠질 수 있을 테니까!

어떻게 하면 좋을까?

일단 탐색중인 경로를 2라고 하고, 탐색을 다 했는데 막혔다면 3으로 채워나가기로 하였다.

    1. 코드를 작성하기에 앞서 **재귀함수를 사용해서 해결**한다는 것을 명심하자.

    2. 사람의 눈은 여러 배열들을 한꺼번에 볼 수 있으나 **컴퓨터는 배열의 전체를 보고 시작하는 것이 아니다.**


![](https://blog.kakaocdn.net/dn/byW4HF/btqyaoNYvLt/DsqCn9VgGWoHRleMe0eGj1/img.png)

![](https://blog.kakaocdn.net/dn/bA204i/btqx8APOxVI/oGGa1jWrTjWBKTP2OApkVk/img.png)

![](https://blog.kakaocdn.net/dn/bfnPZM/btqx7IU4Vrh/JHBymKzibYQ8qtSLGFnAI1/img.png)

![](https://blog.kakaocdn.net/dn/nWzbd/btqx9yjehlt/HpZ9MZnbiKAdAZHaawROW1/img.png)

![](https://blog.kakaocdn.net/dn/ch2rv3/btqx9zoTK3N/RtzBC20QDQdtHilEPYpIK0/img.png)

![](https://blog.kakaocdn.net/dn/beraJq/btqx83qeyjk/bkpuHIzh8zJOcGZblnpv80/img.png)

![](https://blog.kakaocdn.net/dn/cNKwDv/btqx8AvtH4J/DRRRXkdHqIzGVfTeH3uGf1/img.png)

![](https://blog.kakaocdn.net/dn/bLAexO/btqx8ACiyWg/WUPRe0Hof6OSUtXcTnmG11/img.png)

![](https://blog.kakaocdn.net/dn/J2Vg5/btqx8T2ryRF/Iw2gPerUypbrXrqgwMrLdK/img.png)

![](https://blog.kakaocdn.net/dn/cn4pYJ/btqyaoUJQER/6EXZ7mjBWMugtQVlh0tVqK/img.png)

![](https://blog.kakaocdn.net/dn/cmAaYw/btqx8UUz9hm/lI65NObI20uwMYrduu9ZK1/img.png)

![](https://blog.kakaocdn.net/dn/pFWfm/btqx8APOvzs/IjnP3zwFOPRoChspXNSbpK/img.png)

![](https://blog.kakaocdn.net/dn/cjAkTh/btqx7mkvdov/i1SkzmvvnAkJexOUfLs0J0/img.png)

![](https://blog.kakaocdn.net/dn/czItra/btqx9yDxe5z/XhWx4nntP6G3gomv6O2LW1/img.png)

![](https://blog.kakaocdn.net/dn/bjTZcc/btqx8zwy1lV/vdYEXp1Qu1yokZCvfKvt20/img.png)

![](https://blog.kakaocdn.net/dn/9bmuv/btqx8z4s49F/ptaeju6hODx9nglPmF3EM0/img.png)


