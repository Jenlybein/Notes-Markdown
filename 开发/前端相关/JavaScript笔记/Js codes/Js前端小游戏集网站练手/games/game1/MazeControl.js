let playerPos;      // 玩家坐标
let winFlag;        // 胜利防重复判断
let DirMap = new Map([['ArrowLeft',0], ['ArrowRight',1], ['ArrowUp',2], ['ArrowDown',3]]);         // 控制用字典

document.addEventListener('keydown', function(event) {
    // 玩家移动
    let newPos = playerPos + direction[DirMap.get(event.key)];
    if(maze[newPos]){
        playerMove(newPos);
        playerPos = newPos;
    }

    // 终点检测
    if (playerPos === endPos && !winFlag) {
        winFlag = 1;
        setTimeout(function() {
            if (confirm('你赢了！\n点击确定重置迷宫。')) {
                resetMaze();
            }else{
                winFlag = 0;
            }
        },200);
    }
});

// 玩家元素移动
function playerMove(to) {
    let table = document.getElementById('mazeTable');
    let cell_to = table.rows[Math.floor(to / width)].cells[to % width];
    // cell_to.setAttribute("class","wall");
    rect = cell_to.getBoundingClientRect();
    
    let player = document.getElementById("player");
    player.style.top = rect.top + 'px';
    player.style.left = rect.left + 'px';
}

// 重置
function resetMaze(){
    getMazeArr();
    playerPos = width+1;
    drawMaze();
    showToggle = 1;
    playerMove(width+1);
    getTruePath();
    winFlag = 0;
}