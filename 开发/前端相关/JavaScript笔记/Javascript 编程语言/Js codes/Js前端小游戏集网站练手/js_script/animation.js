//处理开始进入画面的圆
setTimeout(function() {
  document.getElementById('circle_begin').style.display = 'flex';

  setTimeout(function() {
    document.getElementById('circle_text').style.display = 'flex';
  },1050);

  setTimeout(function() {
    document.getElementById('circle_begin').style.animation = "background_gradient 10s ease-out infinite"; //圆形移动动画播放完毕后取消移动动画的属性，避免后面不平滑
  
    document.getElementById('circle_begin').addEventListener('click', function() {
        this.classList.add('full-screen');//增加点击事件
        document.getElementById('circle_text').style.animation = "vanishOut 1s ease-out forwards";

        setTimeout(function() {
          document.getElementById('switch_load').style.display = 'flex';
          document.getElementById('menu_load').style.display = 'flex';
        },1050);
        
    });
  }, 1500);

}, 500); //进入页面等待小段时间触发

//菜单点击
function clickAfter(who) {
  document.getElementById('switch_load').classList.toggle('full_screen');

  document.getElementById('menu').classList.toggle('disNo');
  document.getElementById('menu_back').classList.toggle('dis');

  const LCD = document.getElementById('lcd');
  LCD.classList.toggle('lcd_screen');
  LCD.src=who;
}