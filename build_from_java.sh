#!/bin/bash
echo "=== JAVA DOSYALARINDAN APK OLUŞTURMA ==="

# Mevcut Java dosyalarını bul
JAVA_FILES=$(find . -name "*.java" -type f | head -5)
echo "Java dosyaları:"
echo "$JAVA_FILES"

# Android proje yapısını kontrol et
echo "=== ANDROID PROJE YAPISI ==="
find android/ -name "*.xml" -o -name "*.java" | head -10

echo "⚠️  NOT: Gerçek APK için Android SDK ve Gradle gerekiyor"
echo "📱 Şu anki seçenekler:"
echo "1. Android Studio kur"
echo "2. GitHub Actions'ta otomatik build"
echo "3. Manuel APK oluştur (sınırlı)"
