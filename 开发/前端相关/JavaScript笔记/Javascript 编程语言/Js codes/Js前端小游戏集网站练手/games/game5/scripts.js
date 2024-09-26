let correctMaxSum = null;
let correctSubsequence = null;
let numbers = null;

function findMaxSubsequence() {
    const inputElement = document.getElementById('inputNumbers');
    const resultArea = document.getElementById('resultArea');
    const gameStatus = document.getElementById('gameStatus');

    // 清空之前的结果和状态显示
    resultArea.textContent = '';
    gameStatus.textContent = '';

    // 获取输入的数字序列，并转换为数组
    const inputNumbers = inputElement.value.trim();
    if (inputNumbers === '') {
        alert('请输入至少一个数字！');
        return;
    }

    numbers = inputNumbers.split(',').map(num => parseInt(num.trim()));

    // 使用动态规划算法寻找最大子序列和
    let maxSum = numbers[0];
    let currentSum = numbers[0];
    let startIndex = 0;
    let endIndex = 0;
    let tempStartIndex = 0;

    for (let i = 1; i < numbers.length; i++) {
        if (currentSum + numbers[i] < numbers[i]) {
            currentSum = numbers[i];
            tempStartIndex = i;
        } else {
            currentSum += numbers[i];
        }

        if (currentSum > maxSum) {
            maxSum = currentSum;
            startIndex = tempStartIndex;
            endIndex = i;
        }
    }

    // 保存正确答案
    correctMaxSum = maxSum;
    correctSubsequence = numbers.slice(startIndex, endIndex + 1);

    // 显示游戏状态和找到的最大子序列和
    gameStatus.textContent = `当前游戏局面：[${numbers.join(', ')}]`;
    resultArea.textContent = `找到的最大子序列和为：${maxSum}，子序列为 [${correctSubsequence.join(', ')}]`;
}

function generateRandomNumbers() {
    const inputElement = document.getElementById('inputNumbers');
    const minNumber = -10; // 最小数字
    const maxNumber = 10; // 最大数字
    const count = getRandomInt(5, 10); // 生成5到10个数字

    let randomNumbers = [];
    for (let i = 0; i < count; i++) {
        const randomNumber = getRandomInt(minNumber, maxNumber);
        randomNumbers.push(randomNumber);
    }

    inputElement.value = randomNumbers.join(', ');

    // 生成随机数字序列后，计算但不显示对应的最大子序列和
    numbers = randomNumbers;
    calculateMaxSubsequence();
}

function calculateMaxSubsequence() {
    if (numbers === null || numbers.length === 0) {
        alert('没有可用的数字序列！');
        return;
    }

    let maxSum = numbers[0];
    let currentSum = numbers[0];
    let startIndex = 0;
    let endIndex = 0;
    let tempStartIndex = 0;

    for (let i = 1; i < numbers.length; i++) {
        if (currentSum + numbers[i] < numbers[i]) {
            currentSum = numbers[i];
            tempStartIndex = i;
        } else {
            currentSum += numbers[i];
        }

        if (currentSum > maxSum) {
            maxSum = currentSum;
            startIndex = tempStartIndex;
            endIndex = i;
        }
    }

    // 保存正确答案
    correctMaxSum = maxSum;
    correctSubsequence = numbers.slice(startIndex, endIndex + 1);
}

function checkAnswer() {
    const inputMaxSum = document.getElementById('inputMaxSum').value.trim();
    const inputSubsequence = document.getElementById('inputSubsequence').value.trim();
    const answerStatus = document.getElementById('answerStatus');

    if (correctMaxSum === null || correctSubsequence === null) {
        alert('请先生成随机数字序列或输入数字序列并查找最大子序列和！');
        return;
    }

    // 比较玩家输入的答案和实际答案
    const inputSubsequenceArray = inputSubsequence.split(',').map(num => parseInt(num.trim()));
    if (parseInt(inputMaxSum) === correctMaxSum && arraysEqual(inputSubsequenceArray, correctSubsequence)) {
        answerStatus.textContent = '恭喜，答案正确！';
        answerStatus.style.color = 'green';
    } else {
        answerStatus.textContent = '抱歉，答案错误，请再试一次。';
        answerStatus.style.color = 'red';
    }
}

function arraysEqual(arr1, arr2) {
    if (arr1.length !== arr2.length) return false;
    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) return false;
    }
    return true;
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
