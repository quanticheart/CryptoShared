package com.quanticheart.security;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("unused")
public class DeviceID {

    /**
     * getDeviceId() retorna o ID exclusivo do dispositivo.
     * Por exemplo, o IMEI para GSM e o MEID ou ESN para telefones CDMA.
     * Essa solução precisa solicitar android.permission.READ_PHONE_STATE
     *
     * @return IMEI
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public synchronized static String getIMEI(Context context) {
        return ((TelephonyManager) Objects.requireNonNull(context.getSystemService(Context.TELEPHONY_SERVICE))).getDeviceId();
    }

    /**
     * getSubscriberId() retorna o ID exclusivo do assinante,
     * Por exemplo, o IMSI para um telefone GSM.
     * Essa solução precisa solicitar android.permission.READ_PHONE_STATE
     *
     * @return IMSI
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public synchronized static String getIMSI(Context context) {
        return ((TelephonyManager) Objects.requireNonNull(context.getSystemService(Context.TELEPHONY_SERVICE))).getSubscriberId();
    }

    /**
     * Os dispositivos sem serviços de telefonia, como tablets, devem informar um ID
     * de dispositivo exclusivo disponível por android.os.Build.SERIAL desde o Android 2.3 Gingerbread.
     * Alguns telefones que possuem serviços de telefonia também podem definir um número de série.
     * Como nem todos os dispositivos Android têm um número de série, esta solução não é confiável.
     *
     * @return serial
     */
    @SuppressLint("HardwareIds")
    public synchronized static String getSerialID() {
        return Build.SERIAL;
    }

    /**
     * Comoo requisito para a maioria dos aplicativos é identificar uma
     * instalação específica e não um dispositivo físico,
     * uma boa solução para obter um ID exclusivo para um usuário se for usar a classe UUID.
     *
     * @return UUID
     */
    public synchronized static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Na primeira inicialização de um dispositivo, um valor aleatório é gerado e armazenado.
     * Este valor está disponível através de Settings.Secure.ANDROID_ID.
     * É um número de 64 bits que deve permanecer constante durante a vida útil de um dispositivo.
     * ANDROID_ID parece ser uma boa opção para um identificador de dispositivo exclusivo
     * porque está disponível para smartphones e tablets
     *
     * @param context para abrir o getContentResolver()
     * @return ANDROID_ID
     */
    @SuppressLint("HardwareIds")
    public synchronized static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
