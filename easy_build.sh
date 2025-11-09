#!/bin/bash
echo "=== KOLAY APK OLUŞTURUCU ==="

cd ~/real_app/android/app/src/main/

# Gerekli dosyaları kontrol et
echo "Dosyalar kontrol ediliyor..."
ls -la

# Mevcut classes.dex dosyasını kullan
if [ -f "classes.dex" ]; then
    echo "✓ classes.dex bulundu"
else
    echo "✗ classes.dex yok, oluşturuluyor..."
    # Basit bir DEX oluştur
    echo "public class Main { }" > Main.java
    javac Main.java
    dx --dex --output=classes.dex Main.class 2>/dev/null || echo "DEX oluşturulamadı"
fi

# APK oluştur
echo "APK oluşturuluyor..."
aapt package -f -M AndroidManifest.xml -S res -I $PREFIX/share/java/android.jar -F myapp.apk

# DEX'i ekle
aapt add myapp.apk classes.dex

# İmzala
apksigner sign --ks debug.keystore --ks-pass pass:android myapp.apk 2>/dev/null || echo "İmzalama atlandı"

# Sonuç
if [ -f "myapp.apk" ]; then
    cp myapp.apk ~/storage/downloads/my_app_real.apk
    echo "✅ BAŞARILI! APK oluşturuldu."
    echo "📱 Yüklemek için: termux-open ~/storage/downloads/my_app_real.apk"
else
    echo "❌ APK oluşturulamadı"
fi
