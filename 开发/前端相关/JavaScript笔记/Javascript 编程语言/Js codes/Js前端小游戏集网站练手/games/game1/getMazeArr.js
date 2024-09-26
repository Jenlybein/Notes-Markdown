let width;          // 迷宫规格
let maze;           // 迷宫矩阵，1表示墙壁，0表示路径
let direction;      // 方向选取数组
let endPos;         // 标示结束格

// 深度优先打通迷宫
function init(pos) {
    // 获取乱序数组
    let rand = [0, 1, 2, 3];
    for (let i = rand.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [rand[i], rand[j]] = [rand[j], rand[i]]; // 交换元素
    }
    
    // 尝试前进
    for (let i = 0; i < 4; i++) {
        let dir = pos + direction[rand[i]] * 2;
        // 判断是否在界内
        if (!maze[dir] && (dir % width) != 0 && ((dir + 1) % width) != 0 && (dir >= width) && (dir <= endPos)) {
            let dir2 = pos + direction[rand[i]];
            maze[dir] = maze[dir2] = 1; // 变成路
            init(dir);
        }
    }
}

function getMazeArr(size = 25) {
    const inputSize = document.getElementById('inputSize').value;
    if(inputSize>=4){
        size = inputSize;
    }else if(inputSize!=''&&inputSize<4){
        alert("最小规格数字为4");
    }else if(inputSize>80){
        alert("最大规格数字为80");
    }

    width = size | 1;
    endPos = width * (width-1) - 2;
    maze = new Array(width * width).fill(0); // 初始化 maze 数组并设置默认值
    direction = [-1, 1, -width, width];

    init(endPos);
}