// sudoku.js

let solutionBoard = [];

// 数独生成函数
function generateNewGame () {
  const difficulty = document.getElementById("difficulty-select").value;
  const board = generateSudokuBoard(difficulty);
  renderBoard(board);
}

// 数独答案验证函数
function checkSolution () {
  const board = getBoardFromInput();
  if (!isComplete(board)) {
    alert("请填写完整的数独盘面。");
    return;
  }
  if (isValidSudoku(board)) {
    alert("恭喜你，答案正确！");
  } else {
    alert("答案错误，请再试一次。");
  }
}

// 显示答案
function showSolution () {
  renderBoard(solutionBoard, true);
}

// 获取输入的数独盘面
function getBoardFromInput () {
  const board = [];
  for (let row = 0; row < 9; row++) {
    const rowArray = [];
    for (let col = 0; col < 9; col++) {
      const cellValue = document.querySelector(`#cell-${row}-${col} input`).value;
      rowArray.push(cellValue ? parseInt(cellValue) : 0);
    }
    board.push(rowArray);
  }
  return board;
}

// 渲染数独盘面
function renderBoard (board, disableInputs = false) {
  const boardContainer = document.getElementById("sudoku-board");
  boardContainer.innerHTML = "";
  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      const cellDiv = document.createElement("div");
      cellDiv.classList.add("cell");
      const input = document.createElement("input");
      input.type = "text";
      input.maxLength = "1";
      input.value = board[row][col] !== 0 ? board[row][col] : "";
      input.disabled = disableInputs || board[row][col] !== 0;
      cellDiv.id = `cell-${row}-${col}`;
      cellDiv.appendChild(input);
      boardContainer.appendChild(cellDiv);
    }
  }
}

// 数独生成函数 (使用回溯算法保证唯一解)
function generateSudokuBoard (difficulty) {
  const board = Array.from({ length: 9 }, () => Array(9).fill(0));
  fillSudoku(board);
  randomizeBoard(board);
  solutionBoard = board.map(row => row.slice());
  adjustDifficulty(board, difficulty);
  removeNumbers(board, difficulty);
  return board;
}

function fillSudoku (board) {
  const findEmpty = () => {
    for (let r = 0; r < 9; r++) {
      for (let c = 0; c < 9; c++) {
        if (board[r][c] === 0) return [r, c];
      }
    }
    return null;
  };

  const isValid = (num, pos) => {
    const [r, c] = pos;

    for (let i = 0; i < 9; i++) {
      if (board[r][i] === num && i !== c) return false;
    }

    for (let i = 0; i < 9; i++) {
      if (board[i][c] === num && i !== r) return false;
    }

    const boxRow = Math.floor(r / 3) * 3;
    const boxCol = Math.floor(c / 3) * 3;
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (board[boxRow + i][boxCol + j] === num && (boxRow + i !== r || boxCol + j !== c)) return false;
      }
    }

    return true;
  };

  const solve = () => {
    const currPos = findEmpty();
    if (currPos === null) return true;
    const [row, col] = currPos;

    for (let num = 1; num <= 9; num++) {
      if (isValid(num, [row, col])) {
        board[row][col] = num;
        if (solve()) return true;
        board[row][col] = 0;
      }
    }
    return false;
  };

  solve();
}

// 随机变换数独盘面
function randomizeBoard (board) {
  const transformations = [
    swapRows,
    swapColumns,
    swapRowBlocks,
    swapColumnBlocks,
    transpose
  ];

  for (let i = 0; i < transformations.length; i++) {
    const transformation = transformations[Math.floor(Math.random() * transformations.length)];
    transformation(board);
  }
}

function swapRows (board) {
  const block = Math.floor(Math.random() * 3) * 3;
  const row1 = block + Math.floor(Math.random() * 3);
  const row2 = block + Math.floor(Math.random() * 3);
  if (row1 !== row2) {
    const temp = board[row1];
    board[row1] = board[row2];
    board[row2] = temp;
  }
}

function swapColumns (board) {
  const block = Math.floor(Math.random() * 3) * 3;
  const col1 = block + Math.floor(Math.random() * 3);
  const col2 = block + Math.floor(Math.random() * 3);
  if (col1 !== col2) {
    for (let row = 0; row < 9; row++) {
      const temp = board[row][col1];
      board[row][col1] = board[row][col2];
      board[row][col2] = temp;
    }
  }
}

function swapRowBlocks (board) {
  const block1 = Math.floor(Math.random() * 3) * 3;
  let block2 = Math.floor(Math.random() * 3) * 3;
  while (block1 === block2) {
    block2 = Math.floor(Math.random() * 3) * 3;
  }

  for (let i = 0; i < 3; i++) {
    const temp = board[block1 + i];
    board[block1 + i] = board[block2 + i];
    board[block2 + i] = temp;
  }
}

function swapColumnBlocks (board) {
  const block1 = Math.floor(Math.random() * 3) * 3;
  let block2 = Math.floor(Math.random() * 3) * 3;
  while (block1 === block2) {
    block2 = Math.floor(Math.random() * 3) * 3;
  }

  for (let i = 0; i < 9; i++) {
    for (let j = 0; j < 3; j++) {
      const temp = board[i][block1 + j];
      board[i][block1 + j] = board[i][block2 + j];
      board[i][block2 + j] = temp;
    }
  }
}

function transpose (board) {
  for (let i = 0; i < 9; i++) {
    for (let j = i + 1; j < 9; j++) {
      const temp = board[i][j];
      board[i][j] = board[j][i];
      board[j][i] = temp;
    }
  }
}

// 根据难度调整数独盘面
function adjustDifficulty (board, difficulty) {
  // 简单难度只保留少量空白格
  if (difficulty === 'easy') {
    removeNumbers(board, 45);
  }
  // 中等难度保留中等数量的空白格
  else if (difficulty === 'medium') {
    removeNumbers(board, 50);
  }
  // 困难难度保留较多空白格
  else if (difficulty === 'hard') {
    removeNumbers(board, 55);
  }
}

function removeNumbers (board, countToRemove) {
  let attempts = countToRemove;
  while (attempts > 0) {
    let row = Math.floor(Math.random() * 9);
    let col = Math.floor(Math.random() * 9);
    while (board[row][col] === 0) {
      row = Math.floor(Math.random() * 9);
      col = Math.floor(Math.random() * 9);
    }
    const backup = board[row][col];
    board[row][col] = 0;

    const boardCopy = board.map(row => row.slice());
    if (!isUniqueSolution(boardCopy)) {
      board[row][col] = backup;
      attempts--;
    }
  }
}

function isUniqueSolution (board) {
  let solutionCount = 0;

  function solve () {
    const findEmpty = () => {
      for (let r = 0; r < 9; r++) {
        for (let c = 0; c < 9; c++) {
          if (board[r][c] === 0) return [r, c];
        }
      }
      return null;
    };

    const isValid = (num, pos) => {
      const [r, c] = pos;

      for (let i = 0; i < 9; i++) {
        if (board[r][i] === num && i !== c) return false;
      }

      for (let i = 0; i < 9; i++) {
        if (board[i][c] === num && i !== r) return false;
      }

      const boxRow = Math.floor(r / 3) * 3;
      const boxCol = Math.floor(c / 3) * 3;
      for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
          if (board[boxRow + i][boxCol + j] === num && (boxRow + i !== r || boxCol + j !== c)) return false;
        }
      }

      return true;
    };

    const currPos = findEmpty();
    if (currPos === null) {
      solutionCount++;
      return false;
    }
    const [row, col] = currPos;

    for (let num = 1; num <= 9; num++) {
      if (isValid(num, [row, col])) {
        board[row][col] = num;
        if (solve()) return true;
        board[row][col] = 0;
      }
    }
    return false;
  }

  solve();
  return solutionCount === 1;
}

function isValidSudoku (board) {
  const isValid = (num, pos) => {
    const [r, c] = pos;

    for (let i = 0; i < 9; i++) {
      if (board[r][i] === num && i !== c) return false;
    }

    for (let i = 0; i < 9; i++) {
      if (board[i][c] === num && i !== r) return false;
    }

    const boxRow = Math.floor(r / 3) * 3;
    const boxCol = Math.floor(c / 3) * 3;
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (board[boxRow + i][boxCol + j] === num && (boxRow + i !== r || boxCol + j !== c)) return false;
      }
    }

    return true;
  };

  for (let r = 0; r < 9; r++) {
    for (let c = 0; c < 9; c++) {
      if (board[r][c] !== 0 && !isValid(board[r][c], [r, c])) {
        return false;
      }
    }
  }
  return true;
}

function isComplete (board) {
  for (let r = 0; r < 9; r++) {
    for (let c = 0; c < 9; c++) {
      if (board[r][c] === 0) {
        return false;
      }
    }
  }
  return true;
}

// 生成初始游戏局面
generateNewGame();
