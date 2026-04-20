# 📝 Ses Duyarlı Reaksiyon Sistemi

Bu proje, mikrofonu gerçek zamanlı analiz ederek yüksek seste görsel uyarı veren bir Java uygulamasıdır.

## 🚀 Özellikler
- **Anlık Analiz:** RMS algoritması ile ses şiddeti ölçümü.
- **Görsel Uyarı:** Ses eşiği aşılınca ekran yeşilden kırmızıya döner.
- **Always on Top:** Pencere her zaman diğer uygulamaların üstünde kalır.

## 💻 Kurulum ve Çalıştırma
1. `javac SesReaksiyon.java` komutuyla derleyin.
2. `java SesReaksiyon` komutuyla çalıştırın.

## 🛠 Kullanılan Teknolojiler
- Java SE (JDK 8+)
- Java Sound API
- Swing GUI


## 🧠 Çalışma Mantığı (Nasıl Çalışır?)

Program, bilgisayarın mikrofonunu bir "sensör" gibi kullanarak şu üç aşamada tepki verir:

Ses Yakalama (Audio Capture): Java Sound API (TargetDataLine) kullanılarak mikrofon üzerinden gelen analog ses sinyalleri dijital verilere (PCM) dönüştürülür.

Matematiksel Analiz (RMS): Alınan ses verilerinin şiddeti RMS (Root Mean Square) algoritması ile hesaplanır. 
Bu yöntem, ses dalgalarının karelerinin ortalamasının karekökünü alarak bize sesin gerçek "enerji" seviyesini verir.

Karar Mekanizması: Hesaplanan ses seviyesi, kodda belirlenen eşik değeriyle (Threshold) karşılaştırılır. 
Eğer ses bu eşiği geçerse, Swing arayüzü tetiklenerek ekran rengi anlık olarak değişir.
