package com.cieep.ejercicio02.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.cieep.ejercicio02.R;
import com.cieep.ejercicio02.modelos.PagoHora;

import java.text.NumberFormat;
import java.util.List;

public class PagosHoraAdapter extends RecyclerView.Adapter<PagosHoraAdapter.PHVH> {

    private List<PagoHora> objects;
    private int resource;
    private Context context;

    private NumberFormat nfTiempo;
    private NumberFormat nfImporte;

    public PagosHoraAdapter(List<PagoHora> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;

        nfTiempo = NumberFormat.getNumberInstance();
        nfImporte = NumberFormat.getCurrencyInstance();
    }

    @NonNull
    @Override
    public PHVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pagoView = LayoutInflater.from(context).inflate(resource, null);
        pagoView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new PHVH(pagoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PHVH holder, int position) {
        PagoHora ph = objects.get(position);
        holder.lblMatricula.setText(ph.getMatricula());
        holder.lblTiempo.setText(nfTiempo.format(ph.getTiempo()));
        holder.lblImporte.setText(nfImporte.format(ph.getImporte()));

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete(ph, holder.getAdapterPosition()).show();
            }
        });
    }

    private AlertDialog confirmDelete(PagoHora ph, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("SEGURO???");
        builder.setCancelable(false);

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objects.remove(ph);
                notifyItemRemoved(position);
            }
        });
        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public static class PHVH extends RecyclerView.ViewHolder {
        TextView lblMatricula, lblImporte, lblTiempo;
        ImageButton btnEliminar;
        public PHVH(@NonNull View itemView) {
            super(itemView);
            lblMatricula= itemView.findViewById(R.id.lblMatriculaCard);
            lblImporte= itemView.findViewById(R.id.lblImporteCard);
            lblTiempo= itemView.findViewById(R.id.lblTiempoCard);
            btnEliminar = itemView.findViewById(R.id.btnEliminarCard);
        }
    }
}
