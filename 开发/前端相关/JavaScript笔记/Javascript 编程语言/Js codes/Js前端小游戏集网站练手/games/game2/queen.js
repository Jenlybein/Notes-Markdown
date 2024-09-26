        let out_record = Array(8).fill(0);
        const chessboard = document.querySelector('.chessboard');
        for (let row = 0; row < 8; row++) { //添加棋盘
            for (let col = 0; col < 8; col++) {
                const cell = document.createElement('div');
                cell.classList.add('cell');
                cell.dataset.temp = false;
                cell.dataset.place = false;
                cell.addEventListener('click', function (e) {
                    if (e.target.dataset.place === 'false') {
                        e.target.dataset.place = 'true';
                    } else {
                        e.target.dataset.place = 'false';
                    }
                    e.target.classList.toggle('icon');
                })
                if ((row + col) % 2 === 0) {
                    cell.classList.add('brown');
                } else {
                    cell.classList.add('lightbrown');
                }
                chessboard.appendChild(cell);
            }
        }
        let buttons = document.querySelectorAll('.button');

        function init() {
            for (let cell of chessboard.querySelectorAll('.cell')) {
                if (cell.dataset.place === 'true' || cell.dataset.temp === 'true') {
                    cell.dataset.place = 'false';
                    cell.dataset.temp = 'false';
                    cell.classList.toggle('icon');
                }
            }
        }

        function SubmitGameData() {
            let diag1 = Array(15).fill(0);
            let diag2 = Array(15).fill(0);
            let col = Array(8).fill(0);
            let row = Array(8).fill(0);
            cells = chessboard.querySelectorAll('.cell');
            let count = 0;
            for (let i = 0; i < 8; i++) {
                for (let j = 0; j < 8; j++) {
                    if (cells[i * 8 + j].dataset.place === 'true') {
                        count++;
                        if (row[i] != 0 || col[j] != 0 || diag1[i - j + 7] != 0 || diag2[i + j] != 0) {
                            alert("答案错误！");
                            return false;
                        } else {
                            row[i]++;
                            col[j]++;
                            diag1[i - j + 7]++;
                            diag2[i + j]++;
                        }
                    }
                }
            }
            if (count != 8) {
                alert("答案错误！");
                return false;
            }
            alert("恭喜，回答正确！");
            return true;
        }

        function NewGame() {
            init();
            let cells = chessboard.querySelectorAll('.cell');
            let temp_record = Array(8).fill(0); //记录解集
            let record = Array(8).fill(0); //记录解集
            let col = Array(8).fill(0);
            let diag1 = Array(15).fill(0);
            let diag2 = Array(15).fill(0);
            let ans = 0; //记录当前属于第几个解
            let count = Math.floor((Math.random() * 92)); //选取92个解集中的一个
            function dfs(depth) { //处理第depth行的数据
                if (depth == 8) {
                    ans++;
                    if (ans == count) {
                        for (let i = 0; i < 8; i++) {
                            record[i] = temp_record[i]; //皇后放置在第i行第record[i]列
                            out_record[i] = record[i];
                        }
                    }
                    return;
                }
                for (let i = 0; i < 8; i++) {
                    if (col[i] == 0 && diag1[depth - i + 7] == 0 && diag2[depth + i] == 0) {
                        //放置皇后
                        col[i] = diag1[depth - i + 7] = diag2[depth + i] = 1;
                        temp_record[depth] = i;
                        dfs(depth + 1);
                        col[i] = diag1[depth - i + 7] = diag2[depth + i] = 0; //回溯
                    }
                }
            }
            dfs(0); //获取表盘记录
            for (let i = 0; i < 8; i++) { //第i行需不需要放置
                let place = Math.random();
                if (place > 0.5) {
                    cells[i * 8 + record[i]].dataset.place = 'true';
                    cells[i * 8 + record[i]].classList.toggle('icon');

                }
            }
            console.log(record);
            return;
        }

        function showAns() {
            let cells = chessboard.querySelectorAll('.cell');
            for (let i = 0; i < 8; i++) {
                if (cells[i * 8 + out_record[i]].dataset.place === 'false') {
                    cells[i * 8 + out_record[i]].dataset.temp = 'true'; //暂时显示
                    cells[i * 8 + out_record[i]].classList.toggle('icon');
                }
            }
        }
        
        function removeAns() {
            let cells = chessboard.querySelectorAll('.cell');
            for (let i = 0; i < 8; i++) {
                if (cells[i * 8 + out_record[i]].dataset.temp === 'true') {
                    cells[i * 8 + out_record[i]].dataset.temp = 'false';
                    cells[i * 8 + out_record[i]].classList.toggle('icon');
                }
            }
        }
        buttons[0].addEventListener('mousedown', () => {
            setTimeout(showAns, 150);
        });
        buttons[0].addEventListener('mouseup', () => {
            setTimeout(removeAns, 150);
        })
        NewGame();
        buttons[1].addEventListener('click', () => {
            setTimeout(SubmitGameData, 150);
        });
        buttons[2].addEventListener('click', () => {
            NewGame();
            console.log(out_record);
        });
        for (let i = 0; i < buttons.length; i++) { //添加动画监听
            buttons[i].addEventListener("click", (e) => {
                e.preventDefault(); //禁止提交
                let overlay = document.createElement('span');
                overlay.classList.add('overlay');

                let x = e.clientX - e.target.offsetLeft;
                let y = e.clientY - e.target.offsetTop;

                overlay.style.left = x + "px";
                overlay.style.top = y + "px";

                e.target.appendChild(overlay);

                setTimeout(() => {
                    overlay.remove();
                }, 100);
            });
        }