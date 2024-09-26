let truePath;
let pathLen;
let ansFlag;
let cloneMaze;

function dfs(cur,pos){
    if(pos==endPos){
        pathLen = cur;
        ansFlag = true;
    }
    if(ansFlag == true){
        return;
    }
    truePath[cur] = pos;
    for(let i = 0; i < 4; i++){
        let dir = pos + direction[i];
        if (cloneMaze[dir] && (dir % width) != 0 && ((dir + 1) % width) != 0 && (dir >= width) && (dir <= endPos)) {
            cloneMaze[dir] = 0;
            dfs(cur+1,dir);
        }
    }
}

function getTruePath(){
    cloneMaze = [].concat(maze);
    truePath = [];
    ansFlag = false;
    dfs(0,width+1);
}