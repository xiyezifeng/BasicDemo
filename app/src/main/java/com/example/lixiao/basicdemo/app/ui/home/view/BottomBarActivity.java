package com.example.lixiao.basicdemo.app.ui.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.ui.base.activity.BaseActivity;
import com.example.lixiao.basicdemo.app.widget.bottombar.BottomBarLayout;
import com.example.lixiao.basicdemo.support.utils.Utils;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class BottomBarActivity extends BaseActivity {
    private NestedScrollView scrollView;
    private LinearLayout ll_content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_bottom_bar);
        BottomBarLayout bottomBarLayout = findViewById(R.id.bottom_bar);
        bottomBarLayout.setOnClickListeners(new BottomBarLayout.OnClickListener() {
            @Override
            public void onClick(int index) {
                Log.e("BottomBarActivity", "你好啊 : " + index);
            }
        });
        final TextView c = findViewById(R.id.content);
        c.setText("长期的观测和实践表明，云的产生和消散以及各类云之间的演变和转化，都是在一定的水汽条件和大气运动的条件下进行的。人们看不见水汽，也看不见大气运动，但从云的生消演变中可以看到水汽和大气运动的一举一动，而水汽和大气运动对雨、雪、冰、雹等天气现象起着极为重要的作用。\n" +
                "\n" +
                "千百年来，我国劳动人民在生产实践中根据云的形状、来向、移速、厚薄、颜色等的变化，总结了丰富的\"\"看云识天气\"\"的经验，并将这些经验编成谚语。我们在这里将这些有关\"\"看云识天气\"\"的谚语汇总在一起,有兴趣的朋友不妨留心作一些观察对照。\n" +
                "\n" +
                "\"\"天上钩钩云，地上雨淋淋\"\"：钩钩云指钓卷云，这种云的后面，常有锋面(特别是暖锋)、低压或低压槽移来，预兆着阴雨将临；\n" +
                "\n" +
                "\"\"炮台云，雨淋淋\"\"：炮台云指堡状高积云或堡状层积云，多出现在低压槽前，表示空气不稳定，一般隔8－10小时左右有雷雨降临。\n" +
                "\n" +
                "\"\"云交云，雨淋淋\"\"：云交云指上下云层移动方向不一致，也就是说云所处高度的风向不一致，常发生在锋面或低压附近，所以预示有雨，有时云与地面风向相反，则有\"\"逆风行云，天要变\"\"的说法。\n" +
                "\n" +
                "\"\"江猪过河，大雨滂沱\"\"：江猪指雨层云下的碎雨云，出现这种云，表明雨层云中水汽很充足，大雨即将来临。有时碎雨云被大风吹到晴天无云的地方，夜间便看到有象江猪的云飘过\"\"银河\"\"'也是有雨的先兆。\n" +
                "\n" +
                "\"\"棉花云，雨快临\"\"：棉花云指絮状高积云，出现这种云表明中层大气层很不稳定，如果空气中水汽充足并有上升运动，就会形成积雨云，将有雷雨降临。\n" +
                "\n" +
                "\"\"天上灰布悬，雨丝定连绵\"\"：灰布云指雨层云，大多由高层云降低加厚蜕变而成，范围很大、很厚，云中水汽充足，常产生连续性降水。\n" +
                "\n" +
                "\"\"云往东，车马通；云往南，水涨潭；云往西，披蓑衣；云往北，好晒麦\"\"：根据云的移动方向来预测阴晴，云向东、向北移动，预示着天气晴好；云向西、向南移动，预示着会有雨来临。云的移动方向，一般表示它所在高度的风向。这一谚语说明，云在低压内不同部位的分布情况。它适用于密布全天、低而移动较快的云。\n" +
                "\n" +
                "\"\"鱼鳞天，不雨也风颠\"\"：鱼鳞天指卷积云，出现这种云，表明高层大气层不稳定，如果云层继续降低、增厚，说明本地区已处于低压槽前，很快会下雨或刮风。\n" +
                "\n" +
                "\"\"天上鲤鱼斑，明日晒谷不用翻\"\"：鲤鱼斑指透光高积云，往往处在由冷变暖的变性高压气团控制下，云层如果没有继续增厚，短期内仍是晴天。\n" +
                "\n" +
                "\"\"乌云接落日，不落今日落明日\"\"：指太阳落山时，西方地平线下升起一朵城墙似的乌云接住太阳，说明乌云东移，西边阴雨天气系统正在移来，将要下雨。一般来说，如接中云，则当夜有雨；如接高云，则第二天有雨。但如西边的乌云呈条块状或断开，或本地原来就多云，那就不是未来有雨的征兆了。\n" +
                "\n" +
                "\"\"西北开天锁，明朝大太阳\"\"：指阴雨天时，西北方向云层裂开，露出一块蓝天，称\"\"天开锁\"\"。这说明本地已处在阴雨天气系统后部，随着阴雨系统东移，本地将雨止云消，天气转好。\n" +
                "\n" +
                "\"\"太阳现一现，三天不见面\"\"：指春、夏时节，雨天的中午，云层裂开，太阳露一露脸，但云层又很快聚合变厚，这表明本地正处在准静止锋影响下，准静止锋附近气流升降强烈、多变。上升气流增强时，云层变厚，降雨增大；上升气流减弱时，云层变薄，降雨减小或停止；中午前后，太阳照射强烈，云层上部受热蒸发，或云层下面上升气流减弱，天顶处的云层就会裂开。随着太阳照射减弱，或云层下部上升气流加强，裂开的云层又重新聚拢变厚。因此，\"\"太阳现一现\"\"常预示继续阴雨。'这句谚语和\"\"太阳笑，淋破庙\"\"、\"\"亮一亮，下一丈\"\"等谚语类同。\n" +
                "\n" +
                "\"\"日落射脚，三天内雨落\"\"：指太阳从云层的空隙中照射下来，称\"\"日射脚\"\"，傍晚出现日射脚，说明对流作用强烈，预示有雨。\n" +
                "\n" +
                "\"\"早霞不出门，晚霞行千里\"\"：早晨东方无云，西方有云，阳光照到云上散射出彩霞，表明空中水汽充沛或有阴雨系统移来，加上白天空气一般不大稳定，天气将会转阴雨；傍晚如出晚霞，表明西边天空已放晴，加上晚上一般对流减弱，形成彩霞的东方云层，将更向东方移动或趋于消散，预示着天睛。\n" +
                "\n" +
                "\"\"久晴大雾阴，久阴大雾晴\"\"：指的是久晴之后出现雾，说明有暖湿空气移来，空气潮湿，是天阴下雨的征兆；久阴之后出现雾，表明天空中云层变薄裂开消散，地面温度降低而使水汽凝结成辐射雾，持到日出后雾将消去，就会出现晴天。\n" +
                "\n" +
                "在暖季的早晨，如天边出现了堡状云，表示这个高度上的潮湿气层已经很不稳定，到了午间，低层对流一旦发展，上下不稳定的层次结合起来，就会产生强烈的对流运动，形成积雨云而发生雷雨。所以有\"\"清早宝塔云，下午雨倾盆\"\"的谚语。\n" +
                "\n" +
                "另外，有天气预兆的云在演变过程中，往往具有一定的连续性、季节性和地方性。当天空中的云按照卷云、卷层云'高层云、雨层云这样的次序从远处连续移来，而且逐渐由少变多，由高变低，由薄变厚时，就预兆很快会有阴雨天气到来；相反，如果云由低变高、由厚变薄、由成层而崩裂为零散状的云时，就不会有阴雨天气。在暖季早晨，天空如出现底平、顶凸、孤立的云块(淡积云)，或移动较快的白色碎云(碎积云)，表明中低空气层比较稳定，天气睛好。\n" +
                "\n" +
                "此外，云的颜色也可预兆一定的天气，如冰雹云的颜色先是顶白底黑，而后云中出现红色，形成白、黑、红色乱绞的云丝，云边呈土黄色。黑色是阳光透不过云体所造成的；白色是云体对阳光无选择散射或反射的结果；红黄色是云中某些云滴(直径在千分之一到百分之一毫米之间)对阳光进行选择散射的现象。有时雨云也呈现淡黄色，但云色均匀，不乱翻腾。还有不少谚语是从云色和云形来预兆要下冰雹的。例如，内蒙古有\"\"不怕云里黑，就怕云里黑夹红，最怕黄云下面长白虫\"\"等谚语，山西有\"\"黄云翻，冰雹天；乱搅云，雹成群；云打架，雹要下\"\"、\"\"黑云黄云土红云，反来复去乱搅云，多有雹子灾严重\"\"等谚语。还有\"\"午后黑云滚成团，风雨冰雹一齐来\"\"、\"\"天黄闷热乌云翻，天河水吼防冰蛋\"\"等说法，这些都说明当空气对流强盛，云块发展迅猛，象浓烟一股股地直往上冲，云层上下前后翻滚时，就容易下冰雹。 ");

        ll_content = findViewById(R.id.ll_content);
        scrollView = findViewById(R.id.scroll);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                Log.e("BottomBarActivity", "textview height :  " + ll_content.getHeight());
                ll_content.setPadding(0, 0, 0, Utils.dip2px(BottomBarActivity.this, 55 + 10 + 31));
            }
        });

    }
}