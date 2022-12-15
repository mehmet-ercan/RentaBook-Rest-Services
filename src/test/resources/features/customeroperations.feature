Feature: Müşteri işlemleri özelliği
  Scenario: Müşterinin başarılı olarak sisteme eklendiği senaryo
    Given Musteri bilgileri olan "adi", "soyadi" ve "telefonNumarasi" bilgileri alinir
    When Bilgiler ilgili alanlara girildikten sonra kaydet butonuna basilir
    Then Musteri eklenmis olur

    Scenario: Müşteri numarası ile başarılı bir şekilde işlem yapma senaryosu
      Given Musteri numarasi bilinmelidir <5>
      When Musteri numarasi ilgili alana girilir
      Then Islem yapilacak olan musteri sistem var oldugu anlasilir
