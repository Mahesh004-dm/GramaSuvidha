package com.grama.suvidha

object TranslationService {

    private val translations = mapOf(
        // Project names
        "Main Road Repair" to "ಮುಖ್ಯ ರಸ್ತೆ ದುರಸ್ತಿ",
        "Water Supply Pipeline" to "ನೀರು ಸರಬರಾಜು ಪೈಪ್‌ಲೈನ್",
        "Community Hall Construction" to "ಸಮುದಾಯ ಭವನ ನಿರ್ಮಾಣ",
        "Street Light Installation" to "ಬೀದಿ ದೀಪ ಅಳವಡಿಕೆ",
        "Drainage System" to "ಚರಂಡಿ ವ್ಯವಸ್ಥೆ",
        // Types
        "Road" to "ರಸ್ತೆ",
        "Water" to "ನೀರು",
        "Building" to "ಕಟ್ಟಡ",
        "Electricity" to "ವಿದ್ಯುತ್",
        "Sanitation" to "ನೈರ್ಮಲ್ಯ",
        // Locations
        "Ward 1, Hosur" to "ವಾರ್ಡ್ ೧, ಹೊಸೂರು",
        "Ward 2, Hosur" to "ವಾರ್ಡ್ ೨, ಹೊಸೂರು",
        "Ward 3, Hosur" to "ವಾರ್ಡ್ ೩, ಹೊಸೂರು",
        "Ward 4, Hosur" to "ವಾರ್ಡ್ ೪, ಹೊಸೂರು",
        // Statuses
        "Ongoing" to "ಪ್ರಗತಿಯಲ್ಲಿದೆ",
        "Completed" to "ಪೂರ್ಣಗೊಂಡಿದೆ",
        "Pending" to "ಬಾಕಿ ಇದೆ",
        // Descriptions
        "Repair and widening of main village road connecting market area." to "ಮಾರುಕಟ್ಟೆ ಪ್ರದೇಶವನ್ನು ಸಂಪರ್ಕಿಸುವ ಮುಖ್ಯ ಗ್ರಾಮ ರಸ್ತೆಯ ದುರಸ್ತಿ ಮತ್ತು ಅಗಲೀಕರಣ.",
        "New pipeline for clean drinking water supply to 200 households." to "೨೦೦ ಮನೆಗಳಿಗೆ ಶುದ್ಧ ಕುಡಿಯುವ ನೀರು ಸರಬರಾಜಿಗಾಗಿ ಹೊಸ ಪೈಪ್‌ಲೈನ್.",
        "Construction of new community hall for village meetings and events." to "ಗ್ರಾಮ ಸಭೆಗಳು ಮತ್ತು ಕಾರ್ಯಕ್ರಮಗಳಿಗಾಗಿ ಹೊಸ ಸಮುದಾಯ ಭವನ ನಿರ್ಮಾಣ.",
        "Installation of solar street lights across all main roads." to "ಎಲ್ಲಾ ಮುಖ್ಯ ರಸ್ತೆಗಳಲ್ಲಿ ಸೌರ ಬೀದಿ ದೀಪಗಳ ಅಳವಡಿಕೆ.",
        "Underground drainage system to prevent waterlogging." to "ನೀರು ನಿಲ್ಲುವುದನ್ನು ತಡೆಗಟ್ಟಲು ಭೂಗತ ಚರಂಡಿ ವ್ಯವಸ್ಥೆ.",
        // Budgets
        "5,00,000 Rupees" to "₹೫,೦೦,೦೦೦",
        "8,50,000 Rupees" to "₹೮,೫೦,೦೦೦",
        "12,00,000 Rupees" to "₹೧೨,೦೦,೦೦೦",
        "3,00,000 Rupees" to "₹೩,೦೦,೦೦೦",
        "6,50,000 Rupees" to "₹೬,೫೦,೦೦೦",
        "₹5,00,000" to "₹೫,೦೦,೦೦೦",
        "₹8,50,000" to "₹೮,೫೦,೦೦೦",
        "₹12,00,000" to "₹೧೨,೦೦,೦೦೦",
        "₹3,00,000" to "₹೩,೦೦,೦೦೦",
        "₹6,50,000" to "₹೬,೫೦,೦೦೦"
    )

    fun translate(text: String): String {
        return translations[text] ?: text
    }

    fun toKannadaNumber(number: Int): String {
        val kannadaDigits = mapOf(
            '0' to '೦', '1' to '೧', '2' to '೨', '3' to '೩',
            '4' to '೪', '5' to '೫', '6' to '೬', '7' to '೭',
            '8' to '೮', '9' to '೯'
        )
        return number.toString().map { kannadaDigits[it] ?: it }.joinToString("")
    }
}