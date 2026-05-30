package com.example.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class Workshop(
    val id: String,
    val title: String,
    val ageRange: String,
    val description: String,
    val duration: String,
    val highlights: List<String>,
    val imageVector: ImageVector,
    val pricing: String = "Register for quote"
)

data class Product(
    val id: String,
    val title: String,
    val ageRange: String,
    val category: String, // "Swords", "Kits", "Canvases"
    val description: String,
    val price: String,
    val isHandmade: Boolean = true,
    val materials: String,
    val imageVector: ImageVector
)

data class AcademicClass(
    val id: String,
    val title: String,
    val ageRange: String,
    val instructor: String,
    val description: String,
    val schedule: String,
    val focusPoints: List<String>,
    val imageVector: ImageVector,
    val pricing: String = "Contact for details"
)

object PlayData {
    val workshops = listOf(
        Workshop(
            id = "w_islamic_crafts",
            title = "Islamically Integrated Arts & Crafts",
            ageRange = "6–12 years",
            description = "Where kids express creativity while engaging with rich Islamic history, geometry, and ethics. Hands-on projects structured with deep meaning.",
            duration = "2 hours / weekly sessions",
            highlights = listOf("Exploring sacred geometric symmetry", "Learning historical Islamic architecture", "Constructing practical art pieces"),
            imageVector = Icons.Filled.HistoryEdu,
            pricing = "Inquire for Monthly Plans"
        ),
        Workshop(
            id = "w_educational_play",
            title = "Immersive Educational Workshop",
            ageRange = "6–12 years",
            description = "Dynamic workshops blending crafts and experiential learning. Kids build physical products to understand principles of geometry, focus, and discipline.",
            duration = "Half-Day Intensive",
            highlights = listOf("Building wood-based crafts", "Applying practical math to design", "Cooperative team-based challenges"),
            imageVector = Icons.Filled.Lightbulb,
            pricing = "$45 per Session"
        )
    )

    val products = listOf(
        Product(
            id = "p_origami_sword",
            title = "Handmade Origami / Paper Sword",
            ageRange = "4–9 years",
            category = "Wooden Swords",
            description = "Crafted with lightweight, flexible balsa wood and beautifully wrapped in durable traditional origami paper. Safe, imaginative, and perfect for young knights.",
            price = "850 PKR",
            materials = "Premium Origami Paper, Balsa Wood, Cotton Wrap",
            imageVector = Icons.Filled.Shield
        ),
        Product(
            id = "p_light_wood_sword",
            title = "Light Natural Pinewood Sword",
            ageRange = "4–9 years",
            category = "Wooden Swords",
            description = "Perfectly balanced pinewood sword tailored for younger kids. Hand-sanded to a velvety-smooth finish with a soft grip wrap.",
            price = "2,000 PKR",
            materials = "Natural Pinewood, Leatherette Grip",
            imageVector = Icons.Filled.Handyman
        ),
        Product(
            id = "p_wooden_sword_heavy",
            title = "Classic Heavy Wooden Sword",
            ageRange = "10+ years",
            category = "Wooden Swords",
            description = "Solid oak wooden sword built for older kids. Represents discipline, control, and purpose. Durable construction crafted entirely by hand.",
            price = "2,000 PKR",
            materials = "Premium Solid Oak, Linen Cord Wrap",
            imageVector = Icons.Filled.MilitaryTech
        ),
        Product(
            id = "p_diy_string_art",
            title = "DIY String Art Kit - Geometric Star",
            ageRange = "8+ years",
            category = "Craft Kits",
            description = "Complete crafting box. Kids hammer soft brass pins into a pre-drilled wooden base and wrap colored embroidery silks to reveal an Islamic star pattern.",
            price = "2,000 PKR (Without Hammer) / 2,500 PKR (With Hammer)",
            materials = "Solid Pine Base, Brass Pins, Silken Threads",
            imageVector = Icons.Filled.Palette
        ),
        Product(
            id = "p_doodle_canvas",
            title = "Premium Doodle Art Canvas",
            ageRange = "6+ years",
            category = "Canvases",
            description = "Durable canvas stretched on natural pine wood. Intricate, pre-lined floral and geometric doddles waiting to be colored using acrylics, markers, or inks.",
            price = "1,000 PKR",
            materials = "Cotton Canvas, Wooden Frame, Outlining Paint",
            imageVector = Icons.Filled.Brush
        )
    )

    val academicClasses = listOf(
        AcademicClass(
            id = "c_glass_painting",
            title = "Glass Painting Workshop",
            ageRange = "6+ years",
            instructor = "Kamil Sipahi",
            description = "Discover the brilliant, translucent world of glass-stained painting. Kids learn to safely outline and shade beautifully designed panels.",
            schedule = "Saturdays, 10:00 AM",
            focusPoints = listOf("Working with special glass liners", "Color mixing & light considerations", "Framing their transparent canvas"),
            imageVector = Icons.Filled.Category,
            pricing = "2,000 PKR / kid"
        ),
        AcademicClass(
            id = "c_doodle_art",
            title = "Canvas Doodle-Art Painting",
            ageRange = "6+ years",
            instructor = "Kamil Sipahi",
            description = "Stretched canvas on natural pine wood ready for highly creative and customized doodle art painting. Children learn layout, outlining & vibrant acrylic styling.",
            schedule = "Saturdays, 3:30 PM",
            focusPoints = listOf("Custom geometric themes", "Outlining & pattern layering", "Acrylic brush techniques"),
            imageVector = Icons.Filled.Brush,
            pricing = "1,000 PKR / kid"
        ),
        AcademicClass(
            id = "c_pebble_painting",
            title = "Pebble Painting Workshop",
            ageRange = "5+ years",
            instructor = "Kamil Sipahi",
            description = "A relaxing, tactile craft converting natural river pebbles into beautiful works of art. Perfect for developing patience and creative storytelling.",
            schedule = "Saturdays, 1:00 PM",
            focusPoints = listOf("Choosing rocks & paint prep", "Detail painting with micro-brushes", "Creating motivational pebble gifts"),
            imageVector = Icons.Filled.NaturePeople,
            pricing = "700 PKR / kid"
        ),
        AcademicClass(
            id = "c_string_art_workshop",
            title = "String Art Workshop",
            ageRange = "8+ years",
            instructor = "Kamil Sipahi",
            description = "Build a beautiful geometric string art board from scratch. Kids hammer brass pins and thread colorful silks into stunning Islamic and floral stars.",
            schedule = "Saturdays, 5:00 PM",
            focusPoints = listOf("Wood handling & hammer safety", "Geometric symmetry design", "Complex weaving pattern guides"),
            imageVector = Icons.Filled.Palette,
            pricing = "2,500 PKR / kid"
        ),
        AcademicClass(
            id = "c_quranic_arabic",
            title = "Quranic Arabic for Children",
            ageRange = "6–12 years",
            instructor = "Ustadh Kamil Sipahi",
            description = "Making Arabic enjoyable and spiritual. Children learn the rich words, moral stories, and grammar from the Quran through interactive games, boards, and arts.",
            schedule = "Sundays, 10:30 AM",
            focusPoints = listOf("Root-word vocabulary builders", "Stories of the prophets illustrated", "Symmetric writing & tracing crafts"),
            imageVector = Icons.Filled.MenuBook,
            pricing = "Contact for details"
        ),
        AcademicClass(
            id = "c_math_tuition",
            title = "Math Lab with Bilal Adil",
            ageRange = "4–9 years",
            instructor = "Bilal Adil",
            description = "Transform math from abstract formulas into solid, playful concepts! Focuses on rapid math mastery, spatial visualization, and real-world crafts integration.",
            schedule = "Fridays & Sundays (Flexible Slots)",
            focusPoints = listOf("Visual count blocks & origami math", "Multiplication & addition stories", "Puzzles and logic-based craft workshops"),
            imageVector = Icons.Filled.Calculate,
            pricing = "Contact for details"
        )
    )
}
