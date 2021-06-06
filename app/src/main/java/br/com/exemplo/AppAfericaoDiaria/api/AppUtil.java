package br.com.exemplo.AppAfericaoDiaria.api;

import java.util.Calendar;


public class AppUtil {
    public static final int TIME_SPLASH = 5 * 1000;
    public static  final String PREF_APP = "app_afericao_diaria_pref";
    public static  final String LOG_APP = "CLIENTE_LOG";

    /**
     *
     * @return devolve a data atual
     */
    public static String getDataAtual() {
        String dia, mes, ano;
        String dataAtual = "00/00/000";

        try {
            Calendar calendar = Calendar.getInstance();
            dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            mes = String.valueOf(calendar.get(Calendar.MONTH)+1);
            ano = String.valueOf(calendar.get(Calendar.YEAR));

            dia = (Calendar.DAY_OF_MONTH<10) ? "0"+dia : dia;
            int mesAtual = (Calendar.MONTH)+1;
            mes = (mesAtual<10) ? "0"+mes : mes;
            dataAtual = dia+"/"+mes+"/"+ano;

            return dataAtual;

        } catch (Exception e) { }
        return dataAtual;
    }

    /**
     *
     * @return a hora atual
     */
    public static String getHoraAtual() {
        String horaAtual = "10:35:04";
        return horaAtual;
    }
}
