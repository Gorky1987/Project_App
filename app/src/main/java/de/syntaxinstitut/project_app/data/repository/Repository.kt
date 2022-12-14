package de.syntaxinstitut.project_app.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.data.GymSearchApi


import de.syntaxinstitut.project_app.data.datamodels.Blog
import de.syntaxinstitut.project_app.data.datamodels.GymSearch
import de.syntaxinstitut.project_app.data.datamodels.GymSearchList
import de.syntaxinstitut.project_app.data.hView_Item
import de.syntaxinstitut.project_app.data.room.BlogDatabase


const val TAG = "Repository"

class Repository(private val database: BlogDatabase) {

    val api = GymSearchApi

    val blogList: LiveData<List<Blog>> = database.blogDatabaseDao.getAll()

    private val _blog = MutableLiveData<Blog>()
    val blog: LiveData<Blog>
        get() = _blog

    suspend fun insert(blog: Blog) {
        try {
            database.blogDatabaseDao.insert(blog)
        } catch (e: Exception) {
            Log.e(TAG, "Error writing data in database: $e")
        }
    }

    fun getBlog(id: Int) {
        try {
            _blog.postValue(database.blogDatabaseDao.getById(id))
        } catch (e: Exception) {
            Log.e(TAG, "Error finding $id in database: $e")
        }
    }

    suspend fun deleteBlog(id: Int) {
        try {
            database.blogDatabaseDao.deleteById(id)
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting $id from database: $e")
        }
    }

    suspend fun updateGuest(blog: Blog) {
        try {
            database.blogDatabaseDao.update(blog)
        } catch (e: Exception) {
            Log.e(TAG, "Error updating guest in database: $e")
        }
    }

    fun initialBlog(): List<Blog> {
        return listOf(
            Blog(
                1,
                R.drawable.blog_img_01,
                "Blog #1",
                "Personal Trainer",
                "Aufgaben eines...",
                "Zu Beachten:",
                        "Aufgaben eines Fitnesstrainers\n" +
                        "\n" +
                        "Der Fitnesstrainer hat ein breitgef??chertes Spektrum an Aufgaben und T??tigkeiten. \n" +
                        "Er sollte selbstverst??ndlich sehr kundenorientiert sein und hat viel Kontakt mir den Personen, die man trainiert. \n" +
                        "\n" +
                        "In erster Linie ist der Fitnesstrainer, wie der Name schon sagt, f??r die Fitness und die Gesundheit seiner Sch??tzlinge verantwortlich. \n" +
                        "\n" +
                        "Der Trainer sollte sich um jeden Trainierenden individuell k??mmern und ber??cksichtigt dabei seine W??nsche, Ziele, St??rken und Schw??chen oder m??gliche k??rperliche Einschr??nkungen.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Der Ablauf\n" +
                        "\n" +
                        "Zun??chst wird f??r jeden Kunde ein individueller Trainingsplan aufgestellt, hier wird die aktuelle k??rperliche Fitness sowie Verletzungen und Krankheitsbilder ber??cksichtigt. Auch die Krankengeschichte wird beachtet, um so einen m??glichst perfekt abgestimmten Trainingsplan zu erhalten.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Fazit\n" +
                        "\n" +
                        "Nach der Erfassung der individuellen Verfassung der Kunden steht der Fitnesstrainer ihnen beim erreichen ihrer Ziele zur Seite. Diese k??nnen v??llig unterschiedlich ausfallen. \n" +
                        "Ein bekanntes Ziel ist zum Beispiel Gewichtsreduktion, ein anderes die Steigerung der Ausdauer oder der Kraft. Die Ziele m??ssen aber nicht immer k??rperorientiert sein. \n" +
                        "Gesundheitsorientierte Ziele wie die Verbesserung des Wohlbefindens oder die allgemeine St??rkung der Gesundheit sind auch weit verbreitet. \n" +
                        "Der Fitnesstrainer hilft den Leuten, diese Ziele zu erreichen. \n" +
                        "#Motivation w??hrend dem Training spielt dabei eine gro??e Rolle, aber auch generelle Tipps und ein individuell erstellter Ern??hrungsplan soll helfen.\n" +
                        "\n" +
                        "\n"
            ), Blog(
                2,
                R.drawable.blog_img_02,
                "Blog #2",
                "Mobility Training",
                "wie wichtig Flexibilit??t...",
                "Was ist Mobility Training?",
                "\n" +
                        "Mobility Training kombiniert verschiedene Beweglichkeits??bungen, die den Bewegungsradius deines K??rpers vergr????ern. Dazu geh??ren Flexibilit??t, Gleichgewicht, Geschmeidigkeit und Kraft. Die Kombination ist der beste Weg, um Verletzungen vorzubeugen.\n" +
                        "\n" +
                        "Ein riesiger Vorteil von Mobility Training ist seine Vielfalt. Du kannst es einfach an deine Bed??rfnisse und deine Routine anpassen. Eine pre-Workout Beweglichkeits??bung, ein 10-min??tiges Ganzk??rpertraining, oder eine vollst??ndige Yoga-Session ??? Entscheide du, was du gerade brauchst.\n" +
                        "\n" +
                        "Dabei ist es wichtig, immer auf den K??rper zu h??ren. Integriere Mobility Training langsam in deine Routine. Beginne mit ein oder zwei K??rperteilen, anstatt direkt ein Ganzk??rper Mobility Training durchzuziehen.\n" +
                        "Folgst EVO Fitness Instagram. \n" +
                        "\n" +
                        "\n" +
                        "Was ist der Unterschied zwischen Beweglichkeit und Flexibilit??t? \n" +
                        "\n" +
                        "Wissenschaftlich gesehen ist Mobilit??t ???Propriozeption??? ??? unsere Wahrnehmung und unser Bewusstsein f??r die Positionen und Bewegungen unseres K??rpers. \n" +
                        "Mobility Training umfasst daher eine Reihe von ??bungen, \n" +
                        "mit denen du deine Bewegungsfreiheit erh??hst, die Gelenke umgebenden Muskeln kontrollierst und die dir helfen, dich aktiver bewegen zu k??nnen.\n" +
                        "\n" +
                        "Flexibilit??t hingegen ist das Dehnen und Verl??ngern unserer Muskeln. Die Dehnbarkeit und L??nge deines Bindegewebes zu erh??hen hilft dem K??rper, eine Vielzahl an Bewegungen ohne Verletzungen, Steifheit und Schmerzen auszuf??hren",
            ), Blog(
                3,
                R.drawable.blog_img_03,
                "Blog #3",
                "Gesunde & nachhaltige Ern??hrung",
                "kann auch f??r die Umwelt gut sein...",
                "Gesunde & nachhaltige Ern??hrung",
                "\n" +
                        "Gesunde Ern??hrung kann auch f??r die Umwelt gut sein! Erfahre hier, wie Du Dich gesund und gleichzeitig umweltfreundlich ern??hren kannst. \n" +
                        "\n" +
                        "Wusstest Du, dass gesunde Ern??hrung auch oft besser f??r die Umwelt ist? Du tust dabei nicht nur Deinem K??rper etwas gutes, sondern sparst auch CO2- Aussto?? und M??ll. Wir haben 3 Tipps f??r Dich, um Deine Ern??hrung zu verbessern und gleichzeitig die Umwelt zu sch??tzen:\n" +
                        "\n" +
                        "1. Je unverarbeiteter, desto besser\n" +
                        "\n" +
                        "F??r die Natur ist das gut, da die zus??tzlichen Arbeitsschritte, um Nahrung weiter zu verarbeiten, viel Energie kosten. Au??erdem f??llt bei weiterverarbeiteten Produkten meist mehr Verpackungsm??ll an.\n" +
                        "\n" +
                        "F??r Dich ist das gut, da bei der Verarbeitung von Lebensmitteln zum Beispiel zu Fertiggerichten manche Vitamine und N??hrstoffe verloren gehen. Au??erdem wird weiterverarbeitete Nahrung oft mit mehr Fett und Zucker versetzt, als f??r uns gesund ist.\n+" +
                        "\n" +
                        "\n" +
                        "2. Iss wenig tierische Nahrungsmittel\n" +
                        "\n" +
                        "F??r die Natur ist das gut, da Tiere zu halten viel CO2 ausst????t und die G??lle das Grundwasser verschmutzt. Zudem verbraucht der Anbau von Soja als Tierfutter viel Fl??che und daf??r werden Regenw??lder abgeholzt.\n" +
                        "\n" +
                        "F??r Dich ist das gut, da zum Beispiel in N??ssen und Linsen viel ges??ndere Proteine und Fette stecken, als in Fleisch. Zudem nimmst Du weniger Antibiotika, das tieren vorsorglich gegeben wird, zu dir. So entwickelst Du keine Resistenzen gegen sie. Schau dir zu dem Thema auch gerne unseren Beitrag zu Pandemie durch Massentierhaltung an!\n" +
                        "\n" +
                        "\n" +
                        "3. Achte beim Einkauf auf Bio-Qualit??t\n" +
                        "\n" +
                        "F??r die Natur ist das gut, da bei biologischem Anbau weniger Pestizide verwendet werden, die Insekten schaden und ins Grundwasser gelangen. Auch die Tierhaltung ist unter Bio Standards viel umweltfreundlicher. Dazu findest Du noch mehr Info in unserem Beitrag zu Solidarischer Landwirtschaft.\n" +
                        "\n" +
                        "F??r Dich ist das gut, da pestizidfreies Obst und Gem??se ges??nder ist. Bei den tierischen Produkten nimmst Du bei der Bio-Variante au??erdem weniger Antibiotika zu Dir.\n",
            ), Blog(
                4,
                R.drawable.blog_img_04,
                "Blog #4",
                "Cardiovaskul??res Training",
                "oft untersch??tzt...",
                "Cardiovaskul??res Training",
                "\n" +
                        "\n" +
                        "Cardio ??? Was ist das eigentlich und wof??r ist das Ausdauertraining gut?\n" +
                        "\n" +
                        "Neben Krafttraining ist Cardio ein g??ngiger Begriff im Fitnessbereich. Was Cardio ??berhaupt ist, wof??r Cardio Training gut ist, wie dir Cardio beim Abnehmen helfen kann und welche Sportarten dazu z??hlen, erf??hrst du in diesem Beitrag.??\n" +
                        "Cardio Definition: Was ist Cardio Training?\n" +
                        "\n" +
                        "Cardio kommt von dem medizinischen Begriff ???kardiovaskul??r??? und bezieht sich auf Herz und Gef????e. Das Cardio Training umfasst somit alle Ausdauersportarten, welche die Herzfrequenz erh??hen.??\n" +
                        "Was bringt Cardio Training?\n" +
                        "\n" +
                        "Cardio Training wirkt sich je nach Intensit??t und Regelm????igkeit auf die Ausdauer sowie auf die Gesundheit aus. Haupts??chlich wird es genutzt, um Ausdauer aufzubauen und K??rperfett zu reduzieren. Dabei bringt es zahlreiche weitere positive Eigenschaften mit sich.\n" +
                        "\n" +
                        "  -  Durch regelm????iges Cardio Training wird z.B. das Herz ??konomischer arbeiten, indem die Herzfrequenz abnimmt und das Schlagvolumen zunimmt.\n" +
                        "  -  Das Atemvolumen der Lunge wird erh??ht und der K??rper so besser mit Sauerstoff versorgt.\n" +
                        "  -  Die Blutk??rperchen k??nnen sich vermehren, sodass mehr Sauerstoff transportiert wird.\n" +
                        "  -  Durch eine bessere Durchblutung werden Organe und insbesondere auch das Gehirn mit mehr Sauerstoff versorgt, sodass die Konzentrationsf??higkeit gesteigert werden kann.\n" +
                        "  -  Zudem st??rkt Cardio Training das Immunsystem und sch??tzt dadurch vor Krankheiten. ",

                )
        )
    }

    suspend fun fillTableIfEmpty() {

        if (database.blogDatabaseDao.isEmpty()) {
            for (blog in initialBlog()) {

                database.blogDatabaseDao.insert(blog)
            }

        }

    }


    private val _itemList = MutableLiveData<List<hView_Item>>(loadItem())
    val itemList: LiveData<List<hView_Item>>
        get() = _itemList


    fun loadItem(): List<hView_Item> {
        return listOf(
            hView_Item(
                1,
                "Home",
                "back to Homescreen",
                R.drawable.gymguide_full_logo,
                R.drawable._6_post
            ),
            hView_Item(
                2,
                "Gym Finder",
                "search your gym...",
                R.drawable.gymguide_full_logo,
                R.drawable._1_gymfinder
            ),
            hView_Item(
                3,
                "UserProfil",
                "your personal area...",
                R.drawable.gymguide_full_logo,
                R.drawable._2_blog
            ),
            hView_Item(
                4,
                "Die Unterschiede",
                "the differences between the gym...",
                R.drawable.gymguide_full_logo,
                R.drawable._3_unterschiede
            ),
            hView_Item(
                5,
                "Seniorengerecht",
                "these are the right places...",
                R.drawable.gymguide_full_logo,
                R.drawable._4_seniorengrecht
            ),
            hView_Item(
                6,
                "Blog #00",
                "Blogger Guidline",
                R.drawable.gymguide_full_logo,
                R.drawable._5_blog
            ),
            hView_Item(
                7,
                "Die Vorteile",
                "this benefits the muscle...",
                R.drawable.gymguide_full_logo,
                R.drawable._7_item
            ),
        )
    }

    private val _gymSearch = MutableLiveData<List<GymSearch>>()
    val gymSearch: LiveData<List<GymSearch>>
        get() = _gymSearch

    suspend fun getGymSearch(plz: String) {
        try {
            val result = api.retrofitService.getGymSearch("Fitnessstudio+$plz")
            _gymSearch.value = result.searchList
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error loading Gym results from API: $e")
        }
    }
}