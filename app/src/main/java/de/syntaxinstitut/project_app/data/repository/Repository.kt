package de.syntaxinstitut.project_app.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.project_app.R


import de.syntaxinstitut.project_app.data.datamodels.Blog
import de.syntaxinstitut.project_app.data.hView_Item
import de.syntaxinstitut.project_app.data.room.BlogDatabase


const val TAG = "Repository"

class Repository(private val database: BlogDatabase) {

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
                1, "Blog #1", "Personal Trainer", "Aufgaben eines..-",
                "Zu Beachten:", "                                                   \n" +
                        "                                                                     \n" +
                        "\n" +
                        "Aufgaben eines Fitnesstrainers\n" +
                        "\n" +
                        "\n" +
                        "Der Fitnesstrainer hat ein breitgefächertes Spektrum an Aufgaben und Tätigkeiten. \n" +
                        "Er sollte selbstverständlich sehr kundenorientiert sein und hat viel Kontakt mir den Personen, die man trainiert. \n" +
                        "\n" +
                        "In erster Linie ist der Fitnesstrainer, wie der Name schon sagt, für die Fitness und die Gesundheit seiner Schützlinge verantwortlich. \n" +
                        "\n" +
                        "Der Trainer sollte sich um jeden Trainierenden individuell kümmern und berücksichtigt dabei seine Wünsche, Ziele, Stärken und Schwächen oder mögliche körperliche Einschränkungen.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Der Ablauf\n" +
                        "\n" +
                        "Zunächst wird für jeden Kunde ein individueller Trainingsplan aufgestellt, hier wird die aktuelle körperliche Fitness sowie Verletzungen und Krankheitsbilder berücksichtigt. Auch die Krankengeschichte wird beachtet, um so einen möglichst perfekt abgestimmten Trainingsplan zu erhalten.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Fazit\n" +
                        "\n" +
                        "Nach der Erfassung der individuellen Verfassung der Kunden steht der Fitnesstrainer ihnen beim erreichen ihrer Ziele zur Seite. Diese können völlig unterschiedlich ausfallen. \n" +
                        "Ein bekanntes Ziel ist zum Beispiel Gewichtsreduktion, ein anderes die Steigerung der Ausdauer oder der Kraft. Die Ziele müssen aber nicht immer körperorientiert sein. \n" +
                        "Gesundheitsorientierte Ziele wie die Verbesserung des Wohlbefindens oder die allgemeine Stärkung der Gesundheit sind auch weit verbreitet. \n" +
                        "Der Fitnesstrainer hilft den Leuten, diese Ziele zu erreichen. \n" +
                        "#Motivation während dem Training spielt dabei eine große Rolle, aber auch generelle Tipps und ein individuell erstellter Ernährungsplan soll helfen.\n" +
                        "\n" +
                        "\n",
                R.drawable.blog_img_01
            ),

            Blog(
                2,
                "Blog #2",
                "Mobility Training",
                "was ist es?",
                "Was ist Mobility Training?",
                "\n" +
                        "\n" +
                        "Mobility Training kombiniert verschiedene Beweglichkeitsübungen, die den Bewegungsradius deines Körpers vergrößern. Dazu gehören Flexibilität, Gleichgewicht, Geschmeidigkeit und Kraft. Die Kombination ist der beste Weg, um Verletzungen vorzubeugen. \n" +
                        "\n" +
                        "Ein riesiger Vorteil von Mobility Training ist seine Vielfalt. Du kannst es einfach an deine Bedürfnisse und deine Routine anpassen. Eine pre-Workout Beweglichkeitsübung, ein 10-minütiges Ganzkörpertraining, oder eine vollständige Yoga-Session – Entscheide du, was du gerade brauchst.\n" +
                        "\n" +
                        "Dabei ist es wichtig, immer auf den Körper zu hören. Integriere Mobility Training langsam in deine Routine. Beginne mit ein oder zwei Körperteilen, anstatt direkt ein Ganzkörper Mobility Training durchzuziehen.\n" +
                        "Folgst EVO Fitness Instagram. \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Was ist der Unterschied zwischen Beweglichkeit und Flexibilität? \n" +
                        "\n" +
                        "Wissenschaftlich gesehen ist Mobilität „Propriozeption“ – unsere Wahrnehmung und unser Bewusstsein für die Positionen und Bewegungen unseres Körpers. \n" +
                        "Mobility Training umfasst daher eine Reihe von Übungen, \n" +
                        "mit denen du deine Bewegungsfreiheit erhöhst, die Gelenke umgebenden Muskeln kontrollierst und die dir helfen, dich aktiver bewegen zu können.\n" +
                        "\n" +
                        "Flexibilität hingegen ist das Dehnen und Verlängern unserer Muskeln. Die Dehnbarkeit und Länge deines Bindegewebes zu erhöhen hilft dem Körper, eine Vielzahl an Bewegungen ohne Verletzungen, Steifheit und Schmerzen auszuführen",
                R.drawable.blog_img_02
            ), Blog(
                3,
                "Blog #3",
                "Gesunde & nachhaltige Ernährung",
                "kann auch für die Umwelt gut sein...",
                "Gesunde & nachhaltige Ernährung",
                "\n" +
                        "\n" +
                        "\n" +
                        "Gesunde Ernährung kann auch für die Umwelt gut sein! Erfahre hier, wie Du Dich gesund und gleichzeitig umweltfreundlich ernähren kannst. \n" +
                        "\n" +
                        "Wusstest Du, dass gesunde Ernährung auch oft besser für die Umwelt ist? Du tust dabei nicht nur Deinem Körper etwas gutes, sondern sparst auch CO2- Ausstoß und Müll. Wir haben 3 Tipps für Dich, um Deine Ernährung zu verbessern und gleichzeitig die Umwelt zu schützen:\n" +
                        "\n" +
                        "1. Je unverarbeiteter, desto besser\n" +
                        "\n" +
                        "Für die Natur ist das gut, da die zusätzlichen Arbeitsschritte, um Nahrung weiter zu verarbeiten, viel Energie kosten. Außerdem fällt bei weiterverarbeiteten Produkten meist mehr Verpackungsmüll an.\n" +
                        "\n" +
                        "Für Dich ist das gut, da bei der Verarbeitung von Lebensmitteln zum Beispiel zu Fertiggerichten manche Vitamine und Nährstoffe verloren gehen. Außerdem wird weiterverarbeitete Nahrung oft mit mehr Fett und Zucker versetzt, als für uns gesund ist.\n+" +
                        "\n" +
                        "\n" +
                        "2. Iss wenig tierische Nahrungsmittel\n" +
                        "\n" +
                        "Für die Natur ist das gut, da Tiere zu halten viel CO2 ausstößt und die Gülle das Grundwasser verschmutzt. Zudem verbraucht der Anbau von Soja als Tierfutter viel Fläche und dafür werden Regenwälder abgeholzt.\n" +
                        "\n" +
                        "Für Dich ist das gut, da zum Beispiel in Nüssen und Linsen viel gesündere Proteine und Fette stecken, als in Fleisch. Zudem nimmst Du weniger Antibiotika, das tieren vorsorglich gegeben wird, zu dir. So entwickelst Du keine Resistenzen gegen sie. Schau dir zu dem Thema auch gerne unseren Beitrag zu Pandemie durch Massentierhaltung an!\n" +
                        "\n" +
                        "\n" +
                        "3. Achte beim Einkauf auf Bio-Qualität\n" +
                        "\n" +
                        "Für die Natur ist das gut, da bei biologischem Anbau weniger Pestizide verwendet werden, die Insekten schaden und ins Grundwasser gelangen. Auch die Tierhaltung ist unter Bio Standards viel umweltfreundlicher. Dazu findest Du noch mehr Info in unserem Beitrag zu Solidarischer Landwirtschaft.\n" +
                        "\n" +
                        "Für Dich ist das gut, da pestizidfreies Obst und Gemüse gesünder ist. Bei den tierischen Produkten nimmst Du bei der Bio-Variante außerdem weniger Antibiotika zu Dir.\n",
                R.drawable.blog_img_03
            ), Blog(
                4,
                "Blog #4",
                "Cardiovaskuläres Training",
                "oft unterschätzt...",
                "Cardiovaskuläres Training",
                "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Cardio – Was ist das eigentlich und wofür ist das Ausdauertraining gut?\n" +
                        "\n" +
                        "Neben Krafttraining ist Cardio ein gängiger Begriff im Fitnessbereich. Was Cardio überhaupt ist, wofür Cardio Training gut ist, wie dir Cardio beim Abnehmen helfen kann und welche Sportarten dazu zählen, erfährst du in diesem Beitrag. \n" +
                        "Cardio Definition: Was ist Cardio Training?\n" +
                        "\n" +
                        "Cardio kommt von dem medizinischen Begriff „kardiovaskulär“ und bezieht sich auf Herz und Gefäße. Das Cardio Training umfasst somit alle Ausdauersportarten, welche die Herzfrequenz erhöhen. \n" +
                        "Was bringt Cardio Training?\n" +
                        "\n" +
                        "Cardio Training wirkt sich je nach Intensität und Regelmäßigkeit auf die Ausdauer sowie auf die Gesundheit aus. Hauptsächlich wird es genutzt, um Ausdauer aufzubauen und Körperfett zu reduzieren. Dabei bringt es zahlreiche weitere positive Eigenschaften mit sich.\n" +
                        "\n" +
                        "  -  Durch regelmäßiges Cardio Training wird z.B. das Herz ökonomischer arbeiten, indem die Herzfrequenz abnimmt und das Schlagvolumen zunimmt.\n" +
                        "  -  Das Atemvolumen der Lunge wird erhöht und der Körper so besser mit Sauerstoff versorgt.\n" +
                        "  -  Die Blutkörperchen können sich vermehren, sodass mehr Sauerstoff transportiert wird.\n" +
                        "  -  Durch eine bessere Durchblutung werden Organe und insbesondere auch das Gehirn mit mehr Sauerstoff versorgt, sodass die Konzentrationsfähigkeit gesteigert werden kann.\n" +
                        "  -  Zudem stärkt Cardio Training das Immunsystem und schützt dadurch vor Krankheiten. ",
                R.drawable.blog_img_04
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
                "Blog #01",
                "the personal...",
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
}