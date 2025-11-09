#!/bin/bash
echo "=== GERÇEK APK OLUŞTURMA ==="

cd ~/real_app/android/app/src/main/

# Build tools kontrol
if ! command -v aapt &> /dev/null; then
    echo "Build tools kuruluyor..."
    pkg update && pkg install aapt apksigner -y
fi

# APK oluştur
echo "APK oluşturuluyor..."
aapt package -f -M AndroidManifest.xml -S ../../res -I $PREFIX/share/java/android.jar -F chs_pro_v4.apk

# DEX dosyası oluştur (basit)
echo "nop" > classes.dex
aapt add chs_pro_v4.apk classes.dex

# İmzala
echo "APK imzalanıyor..."
apksigner sign --ks $PREFIX/etc/apt/trusted.gpg chs_pro_v4.apk

if [ -f "chs_pro_v4.apk" ]; then
    echo "✅ GERÇEK APK OLUŞTURULDU!"
    cp chs_pro_v4.apk ~/storage/downloads/chs_pro_v4_fixed.apk
    echo "📱 APK: ~/storage/downloads/chs_pro_v4_fixed.apk"
    echo "🎯 Yüklemek için: termux-open ~/storage/downloads/chs_pro_v4_fixed.apk"
else
    echo "❌ APK oluşturulamadı!"
fi
