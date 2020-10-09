#include <iostream>
using namespace std;

int main() {
  int N;
  cin >> N;
  
  int mat[N][N],mat2[N][N];
  
  for(int i = 0;i<N;i++){
    for(int j = 0;j<N;j++){
      cin >> mat[i][j];
    }
  }

  for(int i = 0;i<N;i++){
    for(int j = 0;j<N;j++){
      int total = 0;

      if(mat[i+1][j] ==  1) total+=1;
      if(mat[i][j+1] == 1) total+=1;
      if(mat[i-1][j] == 1) total+=1;
      if(mat[i][j-1] == 1) total+1;
      if(mat[i+1][j+1] == 1) total+=1;
      if(mat[i-1][j+1] == 1) total+=1;
      if(mat[i-1][j-1] == 1) total +=1;
      if(mat[i+1][j-1] == 1) total +=1;

      mat2[i][j] = total;

    }

  }

  for(int i = 0;i<N;i++){
    for(int j = 0;j<N;j++){
      cout << mat2[i][j] << " ";
    }
    cout << endl;
  }
}