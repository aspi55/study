using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.IO;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Security.Cryptography;
using System.Numerics;


namespace WindowsFormsApp1
{
    public partial class RSA : Form
    {
        public RSA()
        {
            InitializeComponent();
        }

        private string pathFile = "";
        private string pathSign = "";
        private string pathOpenKey = "";
        private string pathSecureKey = "";
        private string hashFile;
        private List<string> decHash = new List<string>();
        private List<string> sign = new List<string>();
        private List<string> loadSign = new List<string>();
        private List<string> decSign = new List<string>();
        private ulong a, d, n;
        BigInteger bigExp = new BigInteger();
        BigInteger bigD = new BigInteger();
        BigInteger bigN = new BigInteger();

        BigInteger bigHash = new BigInteger();
        BigInteger bigSign = new BigInteger();
        BigInteger bigSign2 = new BigInteger();
        BigInteger bigLoadSign = new BigInteger();

        private bool isOK, isSK;


        private String openFile()
        {
            openFileDialog1.InitialDirectory = "c:\\";
            openFileDialog1.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
            openFileDialog1.FilterIndex = 1;
            openFileDialog1.RestoreDirectory = true;
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
                pathFile = openFileDialog1.FileName;
            return pathFile;
        }

        private void openSign()
        {
            MessageBox.Show(
            "Загрузите подпись",
            "Загрузите подпись для проверки",
            MessageBoxButtons.OK,
            MessageBoxIcon.Information,
            MessageBoxDefaultButton.Button1,
            MessageBoxOptions.DefaultDesktopOnly);
            openFileDialog1.InitialDirectory = "c:\\";
            openFileDialog1.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
            openFileDialog1.FilterIndex = 1;
            openFileDialog1.RestoreDirectory = true;
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
                pathSign = openFileDialog1.FileName;
            StreamReader sr = new StreamReader(pathSign);
            while (!sr.EndOfStream)
            {
                bigLoadSign = (BigInteger)Convert.ToInt64(sr.ReadLine());
            }
            sr.Close();
        }

        private void loadOpenKey()
        {
            MessageBox.Show(
            "Загрузите ключ для проверки",
            "Загрузите общественный ключ для проверки подписи .txk",
            MessageBoxButtons.OK,
            MessageBoxIcon.Information,
            MessageBoxDefaultButton.Button1,
            MessageBoxOptions.DefaultDesktopOnly);
                openFileDialog1.InitialDirectory = "c:\\";
                openFileDialog1.Filter = "txk files (*.txk)|*.txk|All files (*.*)|*.*";
                openFileDialog1.FilterIndex = 1;
                openFileDialog1.RestoreDirectory = true;
                if (openFileDialog1.ShowDialog() == DialogResult.OK)
                    pathOpenKey = openFileDialog1.FileName;
            if (pathOpenKey != "")
            {
                StreamReader srok = new StreamReader(pathOpenKey);
                while (!srok.EndOfStream)
                {
                    bigD = Convert.ToUInt64(srok.ReadLine());
                    bigN = Convert.ToUInt64(srok.ReadLine());
                    isOK = !Convert.ToBoolean(srok.ReadLine());

                    if (!isOK)
                        MessageBox.Show(
                            "Загружен неверный ключ",
                            "Загрузите ключ для проверки",
                            MessageBoxButtons.OK,
                            MessageBoxIcon.Information,
                            MessageBoxDefaultButton.Button1,
                            MessageBoxOptions.DefaultDesktopOnly);
                }
            }
        }

        private void loadSecureKey()
        {
            MessageBox.Show(
            "Загрузите ключ для подписи",
            "Загрузите секретный ключ для подписи .txk",
            MessageBoxButtons.OK,
            MessageBoxIcon.Information,
            MessageBoxDefaultButton.Button1,
            MessageBoxOptions.DefaultDesktopOnly);
            {
                openFileDialog1.InitialDirectory = "c:\\";
                openFileDialog1.Filter = "txk files (*.txk)|*.txk|All files (*.*)|*.*";
                openFileDialog1.FilterIndex = 1;
                openFileDialog1.RestoreDirectory = true;
                if (openFileDialog1.ShowDialog() == DialogResult.OK)
                    pathSecureKey = openFileDialog1.FileName;
                if (pathSecureKey != "")
                {
                    StreamReader srsk = new StreamReader(pathSecureKey);
                    while (!srsk.EndOfStream)
                    {
                        bigExp = Convert.ToUInt64(srsk.ReadLine());
                        bigN = Convert.ToUInt64(srsk.ReadLine());
                        isSK = Convert.ToBoolean(srsk.ReadLine());
                    }
                    if (!isSK)
                        MessageBox.Show(
                            "Загрузите ключ для подписи",
                            "Загружен неверный ключ",
                            MessageBoxButtons.OK,
                            MessageBoxIcon.Information,
                            MessageBoxDefaultButton.Button1,
                            MessageBoxOptions.DefaultDesktopOnly);
                }
            }
        }
      



        private void saveSign()
        {
            MessageBox.Show(
            "Сохраните подпись",
            "Сохраните подпись документа",
            MessageBoxButtons.OK,
            MessageBoxIcon.Information,
            MessageBoxDefaultButton.Button1,
            MessageBoxOptions.DefaultDesktopOnly);
            saveFileDialog1.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
            saveFileDialog1.FilterIndex = 2;
            saveFileDialog1.DefaultExt = ".txt";
            saveFileDialog1.RestoreDirectory = true;
            saveFileDialog1.FileName = pathFile.Replace(".txt", "Sign.txt");
            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                if (saveFileDialog1.ShowDialog() == DialogResult.OK && saveFileDialog1.FileName.Length > 0)
                {
                    FileInfo fileInfo = new FileInfo(saveFileDialog1.FileName);
                    if (fileInfo.Exists == true)
                        fileInfo.Delete();
                    {
                        using (StreamWriter swriter = new StreamWriter(saveFileDialog1.FileName, true))
                        {
                            swriter.WriteLine(bigSign);
                            swriter.Close();
                        }
                    }
                }
            }
        }
        private void saveSecureKey(ulong d, ulong n)
        {
            saveFileDialog1.Filter = "txk files (*.txk)|*.txk|All files (*.*)|*.*";
            saveFileDialog1.FilterIndex = 1;
            saveFileDialog1.DefaultExt = ".txk";
            saveFileDialog1.RestoreDirectory = true;
            saveFileDialog1.FileName = "Securekey";
            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                if (saveFileDialog1.ShowDialog() == DialogResult.OK && saveFileDialog1.FileName.Length > 0)
                {
                    FileInfo fileInfo = new FileInfo(saveFileDialog1.FileName);
                    if (fileInfo.Exists == true)
                        fileInfo.Delete();
                    {
                        using (StreamWriter swsk = new StreamWriter(saveFileDialog1.FileName, true))
                        {
                            swsk.WriteLine(Convert.ToString(d));
                            swsk.WriteLine(Convert.ToString(n));
                            swsk.WriteLine("true");
                            swsk.Close();
                        }
                    }
                }
            }
        }
        private void saveOpenKey(ulong a, ulong n)
        {
            saveFileDialog1.Filter = "txk files (*.txk)|*.txk|All files (*.*)|*.*";
            saveFileDialog1.FilterIndex = 1;
            saveFileDialog1.DefaultExt = ".txk";
            saveFileDialog1.RestoreDirectory = true;
            saveFileDialog1.FileName = "openkey";
            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                if (saveFileDialog1.ShowDialog() == DialogResult.OK && saveFileDialog1.FileName.Length > 0)
                {
                    FileInfo fileInfo = new FileInfo(saveFileDialog1.FileName);
                    if (fileInfo.Exists == true)
                        fileInfo.Delete();
                    {
                        using (StreamWriter swok = new StreamWriter(saveFileDialog1.FileName, true))
                        {
                            swok.WriteLine(Convert.ToString(a));
                            swok.WriteLine(Convert.ToString(n));
                            swok.WriteLine("false");
                            swok.Close();
                        }
                    }
                }
            }
        }

        void saveDecSign()
        {
            saveFileDialog1.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
            saveFileDialog1.FilterIndex = 2;
            saveFileDialog1.DefaultExt = ".txt";
            saveFileDialog1.RestoreDirectory = true;
            saveFileDialog1.FileName = pathFile.Replace(".txt", "decSign.txt");
            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                if (saveFileDialog1.ShowDialog() == DialogResult.OK && saveFileDialog1.FileName.Length > 0)
                {
                    FileInfo fileInfo = new FileInfo(saveFileDialog1.FileName);
                    if (fileInfo.Exists == true)
                        fileInfo.Delete();

                    using (StreamWriter swriter = new StreamWriter(saveFileDialog1.FileName, true))
                    {
                        foreach (string element in decSign)
                            swriter.WriteLine(element);
                        swriter.Close();
                    }
                }
            }
        }
            
            void saveDecHash()
            {
                saveFileDialog1.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
                saveFileDialog1.FilterIndex = 2;
                saveFileDialog1.DefaultExt = ".txt";
                saveFileDialog1.RestoreDirectory = true;
                saveFileDialog1.FileName = pathFile.Replace(".txt", "decHash.txt");
                if (saveFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    if (saveFileDialog1.ShowDialog() == DialogResult.OK && saveFileDialog1.FileName.Length > 0)
                    {
                    FileInfo fileInfo = new FileInfo(saveFileDialog1.FileName);
                    if (fileInfo.Exists == true)
                        fileInfo.Delete();

                    using (StreamWriter swriter = new StreamWriter(saveFileDialog1.FileName, true))
                        {
                            foreach (string element in decHash)
                                swriter.WriteLine(element);
                            swriter.Close();
                        }
                    }
                }
            }

            

        private BigInteger transformHash() //создание большого хеша в десятичном виде 
        {
            if (pathFile != "")
            {
                ComputeMD5Checksum(pathFile);
                label4.Text = "Файл загружен для подписи или проверки";
                bigHash = 0;
                for (int i = 0; i < hashFile.Length / 6; i += 2)
                {
                    string hashOne = (hashFile[i] + hashFile[i + 1]).ToString();
                    int hashTrans = Convert.ToInt32(hashOne, 16);
                    bigHash = bigHash + (BigInteger)Math.Pow(hashTrans, i);
                }
                //   F6 D5
                bigHash = bigHash / 1000;
            }
            else
            {
                MessageBox.Show(
               "Файл не загружен",
               "Не удалось загрузить файл",
               MessageBoxButtons.OK,
               MessageBoxIcon.Information,
               MessageBoxDefaultButton.Button1,
               MessageBoxOptions.DefaultDesktopOnly);
            }
            return bigHash;
        }


        private BigInteger createSign(BigInteger a, BigInteger n) // зашифровка хеша (десятичного)
        {
            //  bigSign = (BigInteger)(Math.Pow((double)bigHash, a) % (double)n);
            bigSign = bigHash;
            for (int i = 1; i < (int)a; i++)
            {
                bigSign = (bigSign * bigHash) % bigN;
                // label3.Text = Convert.ToString(i);
            }
            return bigSign;

            /*  BigInteger big;
              foreach (string item in decHash)
              {
                  big = new BigInteger(Convert.ToUInt64(item));
                  big = BigInteger.Pow(big, (int)a);
                  big = big % n;
                  UInt64 ind = Convert.ToUInt64(big.ToString());
                  sign.Add(big.ToString());
              }*/

        }


        private void decodeSign(BigInteger bigD, BigInteger bigN)
        {
            bigSign2 = bigLoadSign;
            string str = "";
            for (int i = 1; i < (int)bigD; i++)
            {
                bigSign2 = (bigSign2 * bigLoadSign) % bigN;
                str = Convert.ToString(i);
            }
            //label4.Text = str;
            /*
            BigInteger bigd;
            foreach (string item in loadSign)
            {
                bigd = new BigInteger(Convert.ToUInt64(item));
                bigd = BigInteger.Pow(bigd, (int)d);
                bigd = bigd % n;
                UInt64 ind = Convert.ToUInt64(bigd.ToString());
                decSign.Add(bigd.ToString());
            }*/
        }


        private void GenerationKey() //генерация открытого и закрытого ключа
        {
            ulong p, q;
            BigInteger fn;
            bigExp = 5569; //ключ для зашифровки указано статичное значение, требуется рандомный выбор простого числа
           // ключ для зашифровки закрытая экспонента, простое число, должно быть меньше fn часть открытого ключа СДЕЛАТЬ РАНДОМНУЮ ГЕНЕРАЦИЮ
            p = 10301; //Первое просто число СДЕЛАТЬ РАНДОМНУЮ ГЕНЕРАЦИЮ
            q = 10501;  // второе простое число  СДЕЛАТЬ РАНДОМНУЮ ГЕНЕРАЦИЮ
            bigN = p * q;

       //     n = p * q; //произведение случайных простых чисел, часть открытого ключа
        fn = (p - 1) * (q - 1); //модуль ф (n)

            bigD = bigExp ;
            while (!((bigD * bigExp) % fn == 1))
                bigD++;
            /*  for (d=1; ;d++ ) //поиск ключа дешифровки
                  {
                      if (((d * a) % fn == 1) && (d != a))
                         break;
                  }
                  //label2.Text = "Подпись сгенерирована";
                 */
        }


        

        public string ComputeMD5Checksum(string path) //хеш сумма файла
        {
            using (FileStream fs = System.IO.File.OpenRead(pathFile))
            {
                MD5 md5 = new MD5CryptoServiceProvider();
                byte[] fileData = new byte[fs.Length];
                fs.Read(fileData, 0, (int)fs.Length);
                byte[] checkSum = md5.ComputeHash(fileData);
                hashFile = BitConverter.ToString(checkSum).Replace("-", String.Empty);
                return hashFile;
            }
        }

        private void check()
        {
            if (bigHash==bigSign2)
            MessageBox.Show(
           "Проверка прошла успешно." +
           "Подпись соответствует документу",
           "Проверка подписи",
           MessageBoxButtons.OK,
           MessageBoxIcon.Information,
           MessageBoxDefaultButton.Button1,
           MessageBoxOptions.DefaultDesktopOnly);
            else
            MessageBox.Show(
           "Проверка не пройдена." +
           "Проверьте соответствие подписи и ключа",
           "Проверка подписи",
           MessageBoxButtons.OK,
           MessageBoxIcon.Information,
           MessageBoxDefaultButton.Button1,
           MessageBoxOptions.DefaultDesktopOnly);

          //  label3.Text = Convert.ToString(bigHash);
          //  label4.Text = Convert.ToString(bigLoadSign);
          //  label5.Text = Convert.ToString(bigSign2);
        }

        private void RSA_Load(object sender, EventArgs e)
        {

        }

        private void button6_Click(object sender, EventArgs e)
        {
            loadSecureKey();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            loadOpenKey();
        }

        private void button1_Click(object sender, EventArgs e) //зарузить файл для подписи или проверки
        {
            openFile();
            transformHash();
        }

        private void button3_Click(object sender, EventArgs e) //сгенерировать ключи
        {
            GenerationKey();
            label3.Text = "Ключи сгенерированы и загружены";
            if (checkBox1.Checked)
            {
                saveSecureKey((ulong)bigExp, (ulong)bigN);
                saveOpenKey((ulong)bigD, (ulong)bigN);
            }
        }

        private void button2_Click(object sender, EventArgs e) //Зашифровать подпись из хеша файла и сохранить подпись
        {
            createSign(bigExp, bigN);
            saveSign();
        }

        private void button4_Click(object sender, EventArgs e) //Выбрать подпись документа, расшифровать подпись и вывести результат
        {
            openSign();
            decodeSign(bigD, bigN);
            check();
        }

        private void button5_Click(object sender, EventArgs e) //тестовая кнопка
        {
          label5.Text = Convert.ToString("d=" + bigD + " a=" + bigExp + " n=" + bigN + " Хеш загруженного файла" + bigHash + " Раскодированная подпись" + bigSign2);
        }
    }
}
