# Android
İlk olarak projeye "navigation drawer" için "nav_header" isimli bir layout ekledim. Daha sonra tabları oluşturacağım "TabLayout" ve "ViewPager2" kullanacağım
"content_main" isimli bir layout ekledim.

"content_main" e ilk başta "Toolbar" daha sonra "TabLayout" ve tab'a tıklandığında görüntülemek istediğimiz her şeyi görüntülemek için "ViewPager2" ekledim.
Bu "content_main" i "activity_main" de include olarak ekledim. Sonra "activity_main" e "NavigationView" ekledim.
Daha sonra menu "resource file" ını oluşturdum. "activity_main" e "NavigationView" eklerken menu ve headerLayout'u yazdım.

Herbir tablayout için "First" ve "Second" isimli Fragment oluşturdum.Daha sonra "PagerAdaptor" adında java sınıfı oluşturdum.
"ViewPager2" kullandığım için bu sınıfı "FragmentStateAdapter" sınıfından extend ettim. 
Bu sınıfın içinde bulunan createFragment() methoduyla kullanıcının tıkladığı pozisyona göre uygun fragment'ların gösterimini sağladım.
