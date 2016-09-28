
##缘起
首先说一下这个效果的[来源](https://material.uplabs.com/search?q=dots%20pre)，如图所示（外网网速慢）：

![uplab上的效果](https://assets.materialup.com/uploads/1d048d3d-bc79-40b0-8d79-13149799efa0/preview.gif)

##我的工作
还是挺好看的，我就试着实现了一下，暂时就像先这样，有时间有动力再考虑做个lib
![screenshot](https://github.com/wtus/DotsChangeView/blob/master/screenshot.gif)
##实现的思路
* 有三条 path，分别是上面的弧，下面的弧，中间几个点所在线段的path（用于计算平移的位置）
* 利用 ValueAnimator 构造一个 0-1的变化的值
* 在 ondraw 里分两部分，<0.5,和>0.5 分别画上面和小面，构造两个函数
* 同时根据ValueAnimator的变化量，构造函数，使他移动 gamma 的距离
* 表达能力太次，Talking is cheap,Show me the Code;下班吃饭。

##有哪些坑
* 本来我想用中间那几个点画在bitmap上，然后通过bitmap的canvas平移实现中间那几个点的移动效果
```java
  bitmapCanvas.translate(gamma*i,0);
canvas.drawBitmap(mBitmap,0,0,null);
```
后来发现不行，bitmapCanvas 的 translate 居然是相对的，就是说不如 bitmapCanvas.translate(1,0)，按理说，
不管调用多少次始终只是平移到（-1，0）的位置，但是实际运行运行的效果是，每调用一次，他就平移一次，一直移动。
令我困惑的是，在onDraw（）的参数canvas 又不是相对的，就是绝对平移的。这个是正常的。如果有谁知道，请告知小弟一声啊（微信：hi54wht）.
##MaHua有哪些功能？
