package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicalassignments.R;

import java.util.List;

import activity.shopCarActivity;
import entity.ShopOfShopCar;

public class adapter_lv_shopcar extends BaseAdapter {
    private Context context;
    private Integer layoutId;
    private List<ShopOfShopCar> shopCarList;
    public adapter_lv_shopcar(){};
    public adapter_lv_shopcar(Context context, Integer layoutId, List shopCarList){
        this.context = context;
        this.layoutId = layoutId;
        this.shopCarList = shopCarList;
    }
    @Override
    public int getCount() {
        return shopCarList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopCarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
        }

        ImageView iv_shopcar_img = convertView.findViewById(R.id.iv_shopcar_img);
        TextView tv_shopcar_name = convertView.findViewById(R.id.tv_shopcar_name);
        TextView tv_shopcar_price = convertView.findViewById(R.id.tv_shopcar_price);
        TextView tv_num = convertView.findViewById(R.id.tv_num);

        ShopOfShopCar shop = shopCarList.get(position);
        iv_shopcar_img.setImageResource(shop.getPic());
        tv_shopcar_name.setText(shop.getIntroduce());
        tv_shopcar_price.setText(String.valueOf(shop.getPrice()));
        tv_num.setText(shop.getNum().toString());

        Button btn_reduce = convertView.findViewById(R.id.btn_reduce);
        Button btn_add = convertView.findViewById(R.id.btn_add);
        Button btn_delete = convertView.findViewById(R.id.btn_delete);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop.setNum(shop.getNum() + 1);
                tv_num.setText(shop.getNum().toString());
                Double sumOfPrice = 0.0;
                for(ShopOfShopCar shop1 : shopCarList){
                    sumOfPrice += shop1.getPrice() * shop1.getNum();
                }
                shopCarActivity.tv_sum_price.setText(String.valueOf(sumOfPrice));
            }
        });
        btn_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop.setNum(Math.max(1, shop.getNum() - 1));
                tv_num.setText(shop.getNum().toString());
                Double sumOfPrice = 0.0;
                for(ShopOfShopCar shop1 : shopCarList){
                    sumOfPrice += shop1.getPrice() * shop1.getNum();
                }
                shopCarActivity.tv_sum_price.setText(String.valueOf(sumOfPrice));
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopCarList.remove(shop);
                Double sumOfPrice = 0.0;
                for(ShopOfShopCar shop1 : shopCarList){
                    sumOfPrice += shop1.getPrice() * shop1.getNum();
                }
                shopCarActivity.tv_sum_price.setText(String.valueOf(sumOfPrice));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }




}
