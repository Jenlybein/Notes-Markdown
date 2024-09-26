// 使用表格绘制迷宫
function drawMaze() {
    let size = 500/width;
    let sizeArribute = "width: "+size+"px; height: "+size+"px";

    let table = document.querySelector('table');
    table.innerHTML = ''; // 清空表格内容
    
    let player = document.getElementById("player");
    player.setAttribute("style", sizeArribute);

    for (let i = 0; i < maze.length; i++) {
        let td = document.createElement("td");
        td.setAttribute("style", sizeArribute);
        
        if (i == width) {
            td.setAttribute("class", "startNode");
        } else if (i == endPos+1) {
            td.setAttribute("class", "endNode");
        } else if (maze[i] === 0) {
            td.setAttribute("class", "wall");
        }

        let tr = (i % width==0) ? document.createElement("tr") : table.lastChild;
        table.appendChild(tr);
        tr.appendChild(td);
    }
}


// 展示答案路径
let showToggle; // 答案展示按钮
function showPath() {
    let type = (showToggle)?"path":"";
    showToggle = (showToggle+1)%2;

    let table = document.getElementById('mazeTable');
    
    for(let i = 1, len = pathLen; i < len; i++) {
        let cell = table.rows[Math.floor(truePath[i] / width)].cells[truePath[i] % width];
        cell.setAttribute("class", type);
    }
}