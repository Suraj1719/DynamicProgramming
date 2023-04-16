//Approach-1 
   //brute force 
   //recursion gives tle  time complexity : exponential  space com . : O(n) stack space
class Solution {
    int n;
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
       
        n=piles.size();
        return solve(0,piles,k);
    }
    int solve(int i,List<List<Integer>> piles,int k){
        if(i>=n)
        return 0;

        //we have two case either take or not take
        int not_take=solve(i+1,piles,k);
        int take=0;
        int ans=0;
        for(int j=0;j<Math.min(k,piles.get(i).size());j++){
            ans+=piles.get(i).get(j);
            take=Math.max(take,ans+solve(i+1,piles,k-(j+1)));
        }
    return Math.max(take,not_take);
    }
}

//approach - 2 (dp-memoization on recursion)
//time complexity : O(n*k)  space com . : O(n*k)+O(n) stack space

class Solution {
    int n;
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        
        //recursion + memoization 
        //time complexity : O(n * k)  //space :O(n*k)+ O(n)extra stack space
        n=piles.size();
        int dp[][]=new int[n][k+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        return solve(0,piles,k,dp);
    }
    int solve(int i,List<List<Integer>> piles,int k,int dp[][]){
        if(i>=n)
        return 0;

        if(dp[i][k]!=-1)
           return dp[i][k];
        //we have two case either take or not take
        int not_take=solve(i+1,piles,k,dp);
        int take=0;
        int ans=0;
        for(int j=0;j<Math.min(k,piles.get(i).size());j++){
            ans+=piles.get(i).get(j);
            take=Math.max(take,ans+solve(i+1,piles,k-(j+1),dp));
        }
    return dp[i][k]=Math.max(take,not_take);
    }
}

//approach - 3(tabulation)(for removing extra stack space required in memoization)
class Solution {
    int n;
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        
        //tabulation
        //time complexity : O(n * k)*O(j)  //space :O(n*k)*O(j)
        n=piles.size();
        int dp[][]=new int[n+1][k+1];

        for(int i=n-1;i>=0;i--){
            for(int p=0;p<=k;p++){
                int not_take=dp[i+1][p];
                int take=0;
                int ans=0;
                for(int j=0;j<Math.min(p,piles.get(i).size());j++){
                    ans+=piles.get(i).get(j);
                    take=Math.max(take,ans+dp[i+1][p-(j+1)]);
                }
                dp[i][p]=Math.max(not_take,take);
            }
        }
    return dp[0][k];
    }
}
