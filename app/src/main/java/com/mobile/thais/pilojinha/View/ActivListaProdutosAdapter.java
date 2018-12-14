package com.mobile.thais.pilojinha.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.thais.pilojinha.Manipular_API.URLconfig;
import com.mobile.thais.pilojinha.Model.Produto;
import com.mobile.thais.pilojinha.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

public class ActivListaProdutosAdapter extends ArrayAdapter<Produto> {

    private static final String TAG = "ActivListaProdutosAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView nome;
        TextView preco;
        TextView qtd;
        ImageView image;
    }

    public ActivListaProdutosAdapter(Context context, int resource, List<Produto> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Configura a biblioteca do carregador de imagens
        setupImageLoader();

        //Obtem as informações de pessoas
        String nome = getItem(position).getNome();
        double preco = getItem(position).getPreco();
        double qtd = getItem(position).getQnt();
        String imgUrl = URLconfig.URL_API + "/admin/addimgprod/{id}" + getItem(position).getImagens().get(0).getIdImg().toString();


        //Cria o resultado da view para mostrar a animação
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.nome = convertView.findViewById(R.id.viewNomeProd);
            holder.preco = convertView.findViewById(R.id.viewPrecoProd);
            holder.qtd = convertView.findViewById(R.id.viewQuantProd);
            holder.image = convertView.findViewById(R.id.imgProduto);

            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.nome.setText("Nome: "+ nome);
        holder.preco.setText("Preço: "+ String.valueOf(preco));
        holder.qtd.setText("Quantidade: " + String.valueOf(qtd));

        //Crie o objeto imageloader
        ImageLoader imageLoader = ImageLoader.getInstance();
        ;
        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        //Cria opções de exibição
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //Baixar e exibir imagem do url
        imageLoader.displayImage(imgUrl, holder.image, options);

        return convertView;
    }

    private void setupImageLoader() {
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}
