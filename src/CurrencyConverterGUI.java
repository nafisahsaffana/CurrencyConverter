import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * CurrencyConverterGUI merupakan antarmuka untuk mengkonversi jumlah uang antara
 * berbagai mata uang berdasarkan nilai tukar yang sudah ditentukan.
 */
public class CurrencyConverterGUI extends JFrame implements ActionListener {

    // Input fields dan dropdown untuk pemilihan mata uang
    private JTextField amountField, resultField;
    private JComboBox<String> fromCurrency, toCurrency;
    private JButton convertButton;

    // Menyimpan nilai tukar mata uang yang sudah ditentukan
    private HashMap<String, Double> exchangeRates;

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        loadExchangeRates();

        // Memilih mata uang asal dan tujuan
        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        add(new JLabel("From Currency:"));
        add(fromCurrency);
        add(new JLabel("To Currency:"));
        add(toCurrency);

        // Input field untuk jumlah uang yang akan dikonversi
        amountField = new JTextField();
        add(new JLabel("Amount:"));
        add(amountField);

        // Tombol untuk melakukan konversi mata uang
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        // Output field untuk menampilkan hasil konversi
        resultField = new JTextField();
        resultField.setEditable(false); 
        add(new JLabel("Converted Amount:"));
        add(resultField);

        setVisible(true);
    }

    /**
     * Memuat nilai tukar mata uang yang telah ditentukan ke dalam HashMap.
     * Nilai tukar ini digunakan untuk melakukan konversi antar mata uang.
     */
    private void loadExchangeRates() {
        exchangeRates = new HashMap<>();
        // Nilai tukar mata uang terhadap USD (sebagai mata uang dasar)
        exchangeRates.put("USD", 1.0);       
        exchangeRates.put("EUR", 0.9);       
        exchangeRates.put("JPY", 111.0);     
        exchangeRates.put("GBP", 0.75);      
        exchangeRates.put("IDR", 15000.0);   
    }

    /**
     * Menghitung jumlah yang telah dikonversi berdasarkan mata uang yang dipilih 
     * dan menampilkan hasilnya pada field hasil.
     *
     * @param e ActionEvent yang dipicu oleh klik tombol konversi
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Membaca jumlah yang dimasukkan oleh pengguna
            double amount = Double.parseDouble(amountField.getText());

            // Mendapatkan mata uang asal dan tujuan yang dipilih serta nilai tukar masing-masing
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double fromRate = exchangeRates.get(from);
            double toRate = exchangeRates.get(to);

            // Melakukan perhitungan konversi
            double convertedAmount = (amount / fromRate) * toRate;

            // Menampilkan hasil konversi pada field hasil
            resultField.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException ex) {
            // Menampilkan pesan kesalahan jika input bukan angka yang valid
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metode utama untuk menjalankan aplikasi CurrencyConverterGUI.
     * Aplikasi ini akan menampilkan antarmuka pengguna untuk konversi mata uang.
     *
     * @param args argumen baris perintah (tidak digunakan)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterGUI());
    }
}
