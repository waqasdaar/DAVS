package DAVSClient;


import DAVSDatabaseServer.DAVSDatabase; 
import java.awt.event.ActionEvent; 
import java.awt.event.ItemEvent; 
import java.awt.event.MouseEvent; 
import javax.swing.ButtonGroup; 
import javax.swing.JButton; 
import javax.swing.JCheckBox; 
import javax.swing.JComboBox; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JLayeredPane; 
import javax.swing.JList; 
import javax.swing.JMenu; 
import javax.swing.JMenuBar; 
import javax.swing.JMenuItem; 
import javax.swing.JPanel; 
import javax.swing.JProgressBar; 
import javax.swing.JRadioButton; 
import javax.swing.JRadioButtonMenuItem; 
import javax.swing.JScrollPane; 
import javax.swing.JSeparator; 
import javax.swing.JTextArea; 
import javax.swing.JTextField; 
import javax.swing.event.ListSelectionEvent;
import DAVSMediaCopy.SecureMediaTransfer;
import DAVSPlayer.PlayStreams;
import P2PEngine.PulseP2PEngine;
import RPCClient.ScriptResult;
import java.awt.Cursor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import StreamingEngines.*;
import java.awt.Toolkit;
import java.io.*;
import javax.swing.ImageIcon;



    /*
 * DAVS_Client.java
 * Created on Feb 14, 2009, 11:33:21 PM
 * @Author Waqas Daar
 */


public class DAVS_Client extends javax.swing.JFrame {

    /** Creates new form DAVS_Client */
    public DAVS_Client() {
        initComponents();
        SetFrameComponents();
        SetDAVSServerObjects ();
      }

    @SuppressWarnings("unchecked")
   //public static void main(String args[]) {
     public static void DAVSClientStart (String UserRole) {
        DAVSRole=UserRole;
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DAVS_Client().setVisible(true);
              }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menue_Command_Group = new javax.swing.ButtonGroup();
        Frame_Command_Group = new javax.swing.ButtonGroup();
        Snapshot = new javax.swing.JLabel();
        Media_File = new javax.swing.JLabel();
        Media_File_Path = new javax.swing.JTextField();
        Browse = new javax.swing.JButton();
        StatusBar_Pannel = new javax.swing.JPanel();
        DAVS_Progress = new javax.swing.JProgressBar();
        DAVS_Statusbar_Seperator = new javax.swing.JSeparator();
        davs_status_message = new javax.swing.JLabel();
        Frame_Validate = new javax.swing.JRadioButton();
        Frame_Import = new javax.swing.JRadioButton();
        Frame_Start = new javax.swing.JRadioButton();
        Frame_Stop = new javax.swing.JRadioButton();
        Run_Command = new javax.swing.JButton();
        Labe_Video_Codec = new javax.swing.JLabel();
        Video_Codec = new javax.swing.JComboBox();
        Label_Audio_Codec = new javax.swing.JLabel();
        Audio_Codec = new javax.swing.JComboBox();
        Take_Snapshot = new javax.swing.JCheckBox();
        Label_BitRate = new javax.swing.JLabel();
        BitRate = new javax.swing.JComboBox();
        DAVS_Streams = new javax.swing.JLayeredPane();
        VOD_Streams = new javax.swing.JScrollPane();
        vod_streamslist = new javax.swing.JList();
        broadcast_streams = new javax.swing.JScrollPane();
        b_streams = new javax.swing.JList();
        Label_BroadcastStreams = new javax.swing.JLabel();
        Get_Vod_streams = new javax.swing.JButton();
        Get_Broadcast_streams = new javax.swing.JButton();
        Label_VODStreams = new javax.swing.JLabel();
        Remove_VOD = new javax.swing.JButton();
        Play_VOD = new javax.swing.JButton();
        Remove_LiveStreams = new javax.swing.JButton();
        Play_LiveStream = new javax.swing.JButton();
        Label_Description = new javax.swing.JLabel();
        Description = new javax.swing.JScrollPane();
        Stream_Description = new javax.swing.JTextArea();
        Label_Media_Type = new javax.swing.JLabel();
        Streaming_Type = new javax.swing.JComboBox();
        Label_Streaming_engines = new javax.swing.JLabel();
        Streaming_Engines = new javax.swing.JComboBox();
        davs_snapshot = new javax.swing.JLabel();
        DAVS_Menue = new javax.swing.JMenuBar();
        File_Menue = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Menue_Seperator = new javax.swing.JSeparator();
        Menue_Validate = new javax.swing.JRadioButtonMenuItem();
        Menue_Import = new javax.swing.JRadioButtonMenuItem();
        Menue_Start = new javax.swing.JRadioButtonMenuItem();
        Menue_Stop = new javax.swing.JRadioButtonMenuItem();
        Menue_Deport = new javax.swing.JRadioButtonMenuItem();
        Separator = new javax.swing.JSeparator();
        MenueClose = new javax.swing.JMenuItem();
        About_Menue = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();

        Snapshot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Snapshot.setAlignmentX(0.5F);
        Snapshot.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Snapshot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Snapshot.setName("Snapshot"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Distribution Agnostic Video Server");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(Toolkit.getDefaultToolkit().getImage("//davs.png"));
        setName("DAVS_Frame"); // NOI18N
        setResizable(false);

        Media_File.setText("Media File");
        Media_File.setName("Media_File"); // NOI18N

        Media_File_Path.setEditable(false);
        Media_File_Path.setName("Media_File_Path"); // NOI18N

        Browse.setText("Browse");
        Browse.setName("Browse"); // NOI18N
        Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseActionPerformed(evt);
            }
        });

        StatusBar_Pannel.setName("StatusBar_Pannel"); // NOI18N

        DAVS_Progress.setEnabled(false);
        DAVS_Progress.setName("DAVS_Progress"); // NOI18N
        DAVS_Progress.setOpaque(true);
        DAVS_Progress.setString("");

        DAVS_Statusbar_Seperator.setName("DAVS_Statusbar_Seperator"); // NOI18N

        davs_status_message.setFont(new java.awt.Font("Tahoma", 0, 9));
        davs_status_message.setName("davs_status_message"); // NOI18N

        org.jdesktop.layout.GroupLayout StatusBar_PannelLayout = new org.jdesktop.layout.GroupLayout(StatusBar_Pannel);
        StatusBar_Pannel.setLayout(StatusBar_PannelLayout);
        StatusBar_PannelLayout.setHorizontalGroup(
            StatusBar_PannelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, StatusBar_PannelLayout.createSequentialGroup()
                .addContainerGap()
                .add(davs_status_message, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 364, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 122, Short.MAX_VALUE)
                .add(DAVS_Progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, DAVS_Statusbar_Seperator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        StatusBar_PannelLayout.setVerticalGroup(
            StatusBar_PannelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(StatusBar_PannelLayout.createSequentialGroup()
                .addContainerGap()
                .add(DAVS_Statusbar_Seperator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(StatusBar_PannelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(DAVS_Progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)
                    .add(davs_status_message, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                .addContainerGap())
        );

        davs_status_message.getAccessibleContext().setAccessibleName("davs_statys_message");

        Frame_Command_Group.add(Frame_Validate);
        Frame_Validate.setText("Validate");
        Frame_Validate.setEnabled(false);
        Frame_Validate.setName("Frame_Validate"); // NOI18N
        Frame_Validate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Frame_ValidateItemStateChanged(evt);
            }
        });
        Frame_Validate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Frame_ValidateMouseClicked(evt);
            }
        });

        Frame_Command_Group.add(Frame_Import);
        Frame_Import.setText("Import");
        Frame_Import.setEnabled(false);
        Frame_Import.setName("Frame_Import"); // NOI18N

        Frame_Command_Group.add(Frame_Start);
        Frame_Start.setText("Start");
        Frame_Start.setEnabled(false);
        Frame_Start.setName("Frame_Start"); // NOI18N
        Frame_Start.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Frame_StartItemStateChanged(evt);
            }
        });

        Frame_Command_Group.add(Frame_Stop);
        Frame_Stop.setText("Stop");
        Frame_Stop.setEnabled(false);
        Frame_Stop.setName("Frame_Stop"); // NOI18N
        Frame_Stop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Frame_StopItemStateChanged(evt);
            }
        });

        Run_Command.setText("Run");
        Run_Command.setEnabled(false);
        Run_Command.setName("Run_Command"); // NOI18N
        Run_Command.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Run_CommandActionPerformed(evt);
            }
        });

        Labe_Video_Codec.setText("Video Codec ");
        Labe_Video_Codec.setName("Labe_Video_Codec"); // NOI18N

        Video_Codec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "mpeg4", "mpeg1video", "mpeg2video" }));
        Video_Codec.setEnabled(false);
        Video_Codec.setName("Video_Codec"); // NOI18N

        Label_Audio_Codec.setText("Audio Codec");
        Label_Audio_Codec.setName("Label_Audio_Codec"); // NOI18N

        Audio_Codec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "mp2" }));
        Audio_Codec.setEnabled(false);
        Audio_Codec.setName("Audio_Codec"); // NOI18N

        Take_Snapshot.setText("Take Snapshot");
        Take_Snapshot.setEnabled(false);
        Take_Snapshot.setName("Take_Snapshot"); // NOI18N

        Label_BitRate.setText("Bit Rate");
        Label_BitRate.setName("Label_BitRate"); // NOI18N

        BitRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "100", "200", "300", "400" }));
        BitRate.setEnabled(false);
        BitRate.setName("BitRate"); // NOI18N

        DAVS_Streams.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        DAVS_Streams.setName("DAVS_Streams"); // NOI18N

        VOD_Streams.setName("VOD_Streams"); // NOI18N

        vod_streamslist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vod_streamslist.setName("vod_streamslist"); // NOI18N
        vod_streamslist.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                vod_streamslistValueChanged(evt);
            }
        });
        VOD_Streams.setViewportView(vod_streamslist);

        VOD_Streams.setBounds(60, 10, 220, 150);
        DAVS_Streams.add(VOD_Streams, javax.swing.JLayeredPane.DEFAULT_LAYER);

        broadcast_streams.setName("broadcast_streams"); // NOI18N

        b_streams.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        b_streams.setName("b_streams"); // NOI18N
        b_streams.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                b_streamsValueChanged(evt);
            }
        });
        broadcast_streams.setViewportView(b_streams);

        broadcast_streams.setBounds(350, 10, 210, 150);
        DAVS_Streams.add(broadcast_streams, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Label_BroadcastStreams.setText("<html>Broadcast like Live streams");
        Label_BroadcastStreams.setName("Label_BroadcastStreams"); // NOI18N
        Label_BroadcastStreams.setBounds(290, 10, 60, 50);
        DAVS_Streams.add(Label_BroadcastStreams, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Get_Vod_streams.setText("Get VOD streams");
        Get_Vod_streams.setName("Get_Vod_streams"); // NOI18N
        Get_Vod_streams.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Get_Vod_streamsMouseClicked(evt);
            }
        });
        Get_Vod_streams.setBounds(60, 170, 140, 23);
        DAVS_Streams.add(Get_Vod_streams, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Get_Broadcast_streams.setText("Get Live streams");
        Get_Broadcast_streams.setName("Get_Broadcast_streams"); // NOI18N
        Get_Broadcast_streams.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Get_Broadcast_streamsMouseClicked(evt);
            }
        });
        Get_Broadcast_streams.setBounds(350, 170, 130, 23);
        DAVS_Streams.add(Get_Broadcast_streams, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Label_VODStreams.setText("<html>Video on demand streams");
        Label_VODStreams.setName("Label_VODStreams"); // NOI18N
        Label_VODStreams.setBounds(10, 0, 70, 60);
        DAVS_Streams.add(Label_VODStreams, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Remove_VOD.setText("Remove");
        Remove_VOD.setEnabled(false);
        Remove_VOD.setName("Remove_VOD"); // NOI18N
        Remove_VOD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Remove_VODMouseClicked(evt);
            }
        });
        Remove_VOD.setBounds(60, 200, 220, 23);
        DAVS_Streams.add(Remove_VOD, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Play_VOD.setText("Play");
        Play_VOD.setName("Play_VOD"); // NOI18N
        Play_VOD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Play_VODMouseClicked(evt);
            }
        });
        Play_VOD.setBounds(200, 170, 80, 23);
        DAVS_Streams.add(Play_VOD, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Remove_LiveStreams.setText("Remove");
        Remove_LiveStreams.setEnabled(false);
        Remove_LiveStreams.setName("Remove_LiveStreams"); // NOI18N
        Remove_LiveStreams.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Remove_LiveStreamsMouseClicked(evt);
            }
        });
        Remove_LiveStreams.setBounds(350, 200, 210, 23);
        DAVS_Streams.add(Remove_LiveStreams, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Play_LiveStream.setText("Play");
        Play_LiveStream.setName("Play_LiveStream"); // NOI18N
        Play_LiveStream.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Play_LiveStreamMouseClicked(evt);
            }
        });
        Play_LiveStream.setBounds(480, 170, 80, 23);
        DAVS_Streams.add(Play_LiveStream, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Label_Description.setText("Description");
        Label_Description.setName("Label_Description"); // NOI18N

        Description.setName("Description"); // NOI18N

        Stream_Description.setColumns(20);
        Stream_Description.setEditable(false);
        Stream_Description.setFont(new java.awt.Font("Bookman Old Style", 0, 12));
        Stream_Description.setLineWrap(true);
        Stream_Description.setRows(5);
        Stream_Description.setDoubleBuffered(true);
        Stream_Description.setDragEnabled(true);
        Stream_Description.setName("Stream_Description"); // NOI18N
        Description.setViewportView(Stream_Description);
        Stream_Description.getAccessibleContext().setAccessibleParent(this);

        Label_Media_Type.setText("Streaming Type");
        Label_Media_Type.setName("Label_Media_Type"); // NOI18N

        Streaming_Type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Live Broadcast", "Broadcast Like", "Video on Demand" }));
        Streaming_Type.setEnabled(false);
        Streaming_Type.setName("Streaming_Type"); // NOI18N

        Label_Streaming_engines.setText("Streaming Engine");
        Label_Streaming_engines.setName("Label_Streaming_engines"); // NOI18N

        Streaming_Engines.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        Streaming_Engines.setEnabled(false);
        Streaming_Engines.setName("Streaming_Engines"); // NOI18N
        Streaming_Engines.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Streaming_EnginesItemStateChanged(evt);
            }
        });

        davs_snapshot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        davs_snapshot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        davs_snapshot.setEnabled(false);
        davs_snapshot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        davs_snapshot.setName("davs_snapshot"); // NOI18N

        DAVS_Menue.setName("DAVS_Menue"); // NOI18N

        File_Menue.setText("File");
        File_Menue.setName("File_Menue"); // NOI18N

        Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        Open.setText("Open Media File ");
        Open.setName("Open"); // NOI18N
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DAVS_OpenMediaFile(evt);
            }
        });
        File_Menue.add(Open);

        Menue_Seperator.setName("Menue_Seperator"); // NOI18N
        File_Menue.add(Menue_Seperator);

        Menue_Validate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        Menue_Command_Group.add(Menue_Validate);
        Menue_Validate.setText("Validate");
        Menue_Validate.setEnabled(false);
        Menue_Validate.setName("Menue_Validate"); // NOI18N
        File_Menue.add(Menue_Validate);

        Menue_Import.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        Menue_Command_Group.add(Menue_Import);
        Menue_Import.setText("Import");
        Menue_Import.setEnabled(false);
        Menue_Import.setName("Menue_Import"); // NOI18N
        File_Menue.add(Menue_Import);

        Menue_Start.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Menue_Command_Group.add(Menue_Start);
        Menue_Start.setText("Start");
        Menue_Start.setEnabled(false);
        Menue_Start.setName("Menue_Start"); // NOI18N
        File_Menue.add(Menue_Start);

        Menue_Stop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        Menue_Command_Group.add(Menue_Stop);
        Menue_Stop.setText("Stop");
        Menue_Stop.setEnabled(false);
        Menue_Stop.setName("Menue_Stop"); // NOI18N
        File_Menue.add(Menue_Stop);

        Menue_Deport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        Menue_Command_Group.add(Menue_Deport);
        Menue_Deport.setText("Deport");
        Menue_Deport.setEnabled(false);
        Menue_Deport.setName("Menue_Deport"); // NOI18N
        File_Menue.add(Menue_Deport);

        Separator.setName("Separator"); // NOI18N
        File_Menue.add(Separator);

        MenueClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        MenueClose.setText("Close");
        MenueClose.setName("MenueClose"); // NOI18N
        MenueClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenueCloseMouseClicked(evt);
            }
        });
        File_Menue.add(MenueClose);

        DAVS_Menue.add(File_Menue);

        About_Menue.setText("Help");
        About_Menue.setName("About_Menue"); // NOI18N

        About.setText(" About DAVS");
        About.setName("About"); // NOI18N
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        About_Menue.add(About);

        DAVS_Menue.add(About_Menue);

        setJMenuBar(DAVS_Menue);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(Label_Description)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(26, 26, 26)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(Label_BitRate)
                            .add(Labe_Video_Codec, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .add(Media_File, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(Media_File_Path, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 291, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(Browse))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(Frame_Validate)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(Frame_Import)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(Frame_Start)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(Frame_Stop))
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                                .add(BitRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(66, 66, 66))
                                            .add(Video_Codec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(Label_Media_Type, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                            .add(Label_Audio_Codec, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(Audio_Codec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(Streaming_Type, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                .add(72, 72, 72))
                            .add(layout.createSequentialGroup()
                                .add(Run_Command, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(Label_Streaming_engines)
                                .add(10, 10, 10)
                                .add(Streaming_Engines, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(Take_Snapshot))))
                    .add(layout.createSequentialGroup()
                        .add(Description, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(davs_snapshot, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(DAVS_Streams, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                .addContainerGap())
            .add(StatusBar_Pannel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Media_File, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Media_File_Path, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Browse))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Frame_Validate)
                    .add(Frame_Import)
                    .add(Frame_Start)
                    .add(Frame_Stop))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Run_Command)
                    .add(Streaming_Engines, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Take_Snapshot)
                    .add(Label_Streaming_engines))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Video_Codec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Labe_Video_Codec)
                    .add(Label_Audio_Codec)
                    .add(Audio_Codec, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(Label_BitRate)
                    .add(BitRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Label_Media_Type)
                    .add(Streaming_Type, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(DAVS_Streams, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, Description, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, Label_Description)))
                    .add(layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(davs_snapshot, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 159, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(StatusBar_Pannel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SetFrameComponents () {
        b_streams.setModel(obj_live);
        vod_streamslist.setModel(obj_vod);
        DAVS_Progress.setVisible(false);
        if(DAVSRole.equalsIgnoreCase("user")) {
            Remove_LiveStreams.setVisible(false);
            Remove_LiveStreams.setEnabled(false);
            Remove_VOD.setVisible(false);
            Remove_VOD.setEnabled(false);
        } else {
            Remove_LiveStreams.setVisible(true);
            Remove_LiveStreams.setEnabled(true);
            Remove_VOD.setVisible(true);
            Remove_VOD.setEnabled(true);
        }
      }

    private void SetDAVSServerObjects () {
         objDAVSEngines =new StreamingEngines();
         objPlayStreams =new PlayStreams();
         objDAVSServer  =new DAVSDatabase();
         objLive555     =new Live555();
         objRTSP        =new RTSP();
         objPulse       =new PulseP2PEngine();
         obj            =new SecureMediaTransfer();
         obj.CreatePulseStreamDirectory();
         obj.CreateSnapshotDirectory();
    }
    
    private void GetStreamingEngines () {
       if(Streaming_Engines.getItemCount()<=1) {
            StreamingEngine=objDAVSEngines.GetStreamingEngines();
            StringTokenizer engine=new StringTokenizer(StreamingEngine," ");
              while (engine.hasMoreTokens()) {
                  Streaming_Engines.addItem(engine.nextToken().toUpperCase());
                }
          }
    }


    private void StartStatusBar (String strMessage) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DAVS_Progress.setVisible(true);
        DAVS_Progress.setStringPainted(true);
        DAVS_Progress.setIndeterminate(true);
        davs_status_message.setText(strMessage);
    }
    private void StopStatusBar () {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        DAVS_Progress.setVisible(false);
        DAVS_Progress.setStringPainted(false);
        DAVS_Progress.setIndeterminate(false);
        davs_status_message.setText("");
    }
    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
          if(About==evt.getSource()) {
             dialog = new DAVS_About(new javax.swing.JFrame(), true);
             dialog.setVisible(true);
          }
    }//GEN-LAST:event_AboutActionPerformed

   
    private void BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseActionPerformed
             if(Browse==evt.getSource())
           {
                 MediaFileChooser= new JFileChooser();
                 NeedsTranscoding=false;
                 int iMediaFile_Value=MediaFileChooser.showOpenDialog(MediaFileChooser);

                 if(iMediaFile_Value==JFileChooser.OPEN_DIALOG)
                 {
                     String strStreamPath=MediaFileChooser.getSelectedFile().toString();
                     Media_File_Path.setText(strStreamPath);
                     File objfile= new File(Media_File_Path.getText());
                     MediaStreamName=objfile.getName();
                     int  iDotPos=MediaStreamName.lastIndexOf(".")+1;
                     MediaStreamExtension=MediaStreamName.substring(iDotPos);

                    if(MediaStreamExtension.equalsIgnoreCase("sdp") || MediaStreamExtension.equalsIgnoreCase("SDP"))
                    {
                     IsSDP=true;
                     Menue_Validate.setEnabled(rootPaneCheckingEnabled);
                     Frame_Validate.setEnabled(rootPaneCheckingEnabled);
                     Run_Command.setEnabled(rootPaneCheckingEnabled);
                     Streaming_Engines.setEnabled(true);
                     GetStreamingEngines();
                     SetDAVSServerObjects();
                     Take_Snapshot.setSelected(false);
                    }
                    else
                    {
                     IsSDP=false;
                     Menue_Validate.setEnabled(rootPaneCheckingEnabled);
                     Frame_Validate.setEnabled(rootPaneCheckingEnabled);
                     Run_Command.setEnabled(rootPaneCheckingEnabled);
                     Streaming_Engines.setEnabled(true);
                     GetStreamingEngines();
                     SetDAVSServerObjects();
                    }
                 }
                 else
                 {
                     Media_File_Path.setText("");
                     Menue_Validate.setSelected(false);
                     Menue_Validate.setEnabled(false);
                     Frame_Validate.setEnabled(false);
                     Frame_Validate.setSelected(false);
                     Run_Command.setEnabled(false);
                     Streaming_Engines.setEnabled(false);
                     Run_Command.setText("Run");
                     Take_Snapshot.setEnabled(false);
                 }
           }
    }//GEN-LAST:event_BrowseActionPerformed

    private void DAVS_OpenMediaFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DAVS_OpenMediaFile
           if(Open==evt.getSource())
           {
                 MediaFileChooser= new JFileChooser();
                 NeedsTranscoding=false;
                 int iMediaFile_Value=MediaFileChooser.showOpenDialog(MediaFileChooser);
                 
                 if(iMediaFile_Value==JFileChooser.OPEN_DIALOG)
                 {
                     String strStreamPath=MediaFileChooser.getSelectedFile().toString();
                     Media_File_Path.setText(strStreamPath);
                     File objfile= new File(Media_File_Path.getText());
                     MediaStreamName=objfile.getName();
                     int  iDotPos=MediaStreamName.lastIndexOf(".")+1;
                     MediaStreamExtension=MediaStreamName.substring(iDotPos);
                     
                    if(MediaStreamExtension.equalsIgnoreCase("sdp") || MediaStreamExtension.equalsIgnoreCase("SDP"))
                    {
                     IsSDP=true;
                     Menue_Validate.setEnabled(rootPaneCheckingEnabled);
                     Frame_Validate.setEnabled(rootPaneCheckingEnabled);
                     Run_Command.setEnabled(rootPaneCheckingEnabled);
                     Streaming_Engines.setEnabled(true);
                     GetStreamingEngines();
                     SetDAVSServerObjects();
                    }
                    else
                    {
                     IsSDP=false;
                     Menue_Validate.setEnabled(rootPaneCheckingEnabled);
                     Frame_Validate.setEnabled(rootPaneCheckingEnabled);
                     Run_Command.setEnabled(rootPaneCheckingEnabled);
                     Take_Snapshot.setEnabled(false);
                     Streaming_Engines.setEnabled(true);
                     GetStreamingEngines();
                     SetDAVSServerObjects();
                    }
                 }
                 else
                 {
                     Media_File_Path.setText("");
                     Menue_Validate.setSelected(false);
                     Frame_Command_Group.setSelected(Frame_Validate.getModel(), false);
                     Menue_Validate.setEnabled(false);
                     Frame_Validate.setEnabled(false);
                     Frame_Validate.setSelected(false);
                     Run_Command.setEnabled(false);
                     Run_Command.setText("Run");
                     Take_Snapshot.setEnabled(false);
                     Streaming_Engines.setEnabled(false);
                 }
           }
    }//GEN-LAST:event_DAVS_OpenMediaFile

    private void Run_CommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Run_CommandActionPerformed
             if(Run_Command==evt.getSource())
                  {
                      if( Frame_Start.isSelected() || Menue_Start.isSelected() ) {
                           int Answer=JOptionPane.showOptionDialog(null,"Do you want to start the P2P Pulse stream.\n"
                                 ,"DAVS Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                           if(Answer==JOptionPane.YES_OPTION) {
                              P2P_Start();
                           } else {
                              Start_Live_Stream();
                           }
                      }
                      else if ( Frame_Stop.isSelected() || Menue_Stop.isSelected() ) {
                           int Answer=JOptionPane.showOptionDialog(null,"Do you want to stop the Pulse P2P stream.\n"
                                 ,"DAVS Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                           if(Answer==JOptionPane.YES_OPTION) {
                                    Object[] PulseMode = {"source", "client"};
                                    String Mode = (String)JOptionPane.showInputDialog(
                                    null,"Please select the Pulse mode.",
                                    "DAVS Message",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,PulseMode,"source");
                                    Stop_P2P(Mode);

                           } else {
                                  Stop_Live_Stream();
                           }
                         
                      }
                     else if( Frame_Validate.isSelected())
                       {
                         if (Media_File_Path.getText().matches("")) {
                           JOptionPane.showMessageDialog(null,"Please select the media stream first.","DAVS Error",JOptionPane.ERROR_MESSAGE);
                          }
                         else 
                         {
                            if(Streaming_Engines.getSelectedIndex()==0) {
                                JOptionPane.showMessageDialog(null,"Please choose the streaming engine.",
                                                    "DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                              }  
                            else 
                            {
                                
                                StartStatusBar("validating the media stream for "+Streaming_Engines.getSelectedItem().toString()+" streaming engine....");
                         if(obj.SecureCopyFromLocalMachine(Media_File_Path.getText().trim())) {
                             if(IsSDP)  {
                                  if(Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("RTSP"))
                                  {
                                        int IsValid = objRTSP.RTSP_Validate(DAVSServer.RemotrDirectory.concat(MediaStreamName), 0);
                                        if (IsValid==0)
                                        {
                                            StopStatusBar();
                                            int Result=JOptionPane.showOptionDialog(null,"SDP file has been successfuly validated.\n"+
                                                                              "Do you want to import into the streaming engine.","DAVS Message",
                                                                               JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                                            if (Result==JOptionPane.YES_OPTION) {
                                                 Frame_Import.setEnabled(true);
                                                 Menue_Import.setEnabled(true);
                                                 Streaming_Type.setEnabled(true);
                                                 Frame_Command_Group.setSelected(Frame_Import.getModel(), true);
                                                 Frame_Validate.setEnabled(false);
                                                 Frame_Validate.setSelected(false);
                                                 Streaming_Engines.setEnabled(false);

                                              }
                                            else {
                                               Media_File_Path.setText("");
                                               Take_Snapshot.setSelected(false);
                                               Frame_Validate.setSelected(false);
                                               Menue_Command_Group.setSelected(Menue_Validate.getModel(),false);
                                               Streaming_Type.setEnabled(false);
                                               Streaming_Engines.setSelectedIndex(0);
                                            }
                                        }
                                        else
                                        {
                                          JOptionPane.showMessageDialog(null,"Format of the SDP file is not correct.\n"+
                                                                              "Please correct the SDP file.","DAVS Message",
                                                                               JOptionPane.INFORMATION_MESSAGE);
                                          Media_File_Path.setText("");
                                          Take_Snapshot.setSelected(false);
                                          Frame_Validate.setSelected(false);
                                          Menue_Command_Group.setSelected(Menue_Validate.getModel(),false);
                                          Streaming_Type.setEnabled(false);
                                          Streaming_Engines.setSelectedIndex(0);
                                          Streaming_Engines.setEnabled(false);
                                        }
                                  }
                                else {
                                      StopStatusBar();
                                      JOptionPane.showMessageDialog(null,Streaming_Engines.getSelectedItem()+" does not support SDP streaming.",
                                                     "DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                      Media_File_Path.setText("");
                                      Take_Snapshot.setSelected(false);
                                      Frame_Validate.setSelected(false);
                                      Menue_Command_Group.setSelected(Menue_Validate.getModel(),false);
                                      Streaming_Type.setEnabled(false);
                                      Streaming_Engines.setSelectedIndex(0);
                                      Streaming_Engines.setEnabled(false);
                                  }
                             }
                             else
                             {

                                if(Take_Snapshot.isSelected())
                                {
                                     StartStatusBar("validating the media stream for "+Streaming_Engines.getSelectedItem().toString()+" streaming engine....");
                                     int IsValidate=10;
                                     if(Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("RTSP")) {
                                      IsValidate=objRTSP.RTSP_Validate(DAVSServer.RemotrDirectory.concat(MediaStreamName), 1);
                                     }
                                     if(Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("PULSE")) {
                                        IsValidate=objPulse.P2P_Validate(DAVSServer.RemotrDirectory.concat(MediaStreamName));
                                     }else {
                                       IsValidate=objLive555.Live555_Validate(DAVSServer.RemotrDirectory.concat(MediaStreamName), 1);
                                     }
                                     if (IsValidate==0) {
                                         StopStatusBar();
                                         int result= JOptionPane.showOptionDialog(null,"Media stream has been successfuly validated.\n"+
                                               "Do you want to import into "+Streaming_Engines.getSelectedItem().toString().trim()+" streaming engine."
                                               ,"DAVS Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                                            if(result==JOptionPane.YES_OPTION) {
                                                 Take_Snapshot.setEnabled(false);
                                                 Frame_Import.setEnabled(true);
                                                 Frame_Command_Group.setSelected(Frame_Import.getModel(), true);
                                                 Frame_Validate.setEnabled(false);
                                                 Frame_Validate.setSelected(false);
                                                 Menue_Import.setEnabled(true);
                                                 Streaming_Engines.setEnabled(false);
                                                 Streaming_Type.setEnabled(true);
                                            }
                                         if (result==JOptionPane.NO_OPTION) {
                                             Media_File_Path.setText("");
                                             Take_Snapshot.setSelected(false);
                                             Frame_Validate.setSelected(false);
                                             Menue_Command_Group.setSelected(Menue_Validate.getModel(),false);
                                             Streaming_Type.setEnabled(false);
                                         }
                                      }
                                     else {
                                          StopStatusBar();
                                         int ImportOption=JOptionPane.showOptionDialog(null,"Media stream need some transcoding.\n"+
                                                                                         "Do you want to import into the streaming engine? then please set the parameters.","DAVS Message",
                                                                                         JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                                              if(ImportOption==JOptionPane.YES_OPTION) {
                                                   NeedsTranscoding=true;
                                                   Frame_Import.setEnabled(true);
                                                   Menue_Import.setEnabled(true);
                                                   Video_Codec.setEnabled(true);
                                                   Audio_Codec.setEnabled(true);
                                                   Frame_Command_Group.setSelected(Frame_Import.getModel(), true);
                                                   Frame_Validate.setSelected(false);
                                                   BitRate.setEnabled(true);
                                                   Streaming_Type.setEnabled(true);
                                                   Streaming_Engines.setEnabled(false);
                                                 }
                                              if(ImportOption==JOptionPane.NO_OPTION) {
                                                 NeedsTranscoding=false;
                                                 Video_Codec.setEnabled(false);
                                                 Audio_Codec.setEnabled(false);
                                                 BitRate.setEnabled(false);
                                                 Streaming_Type.setEnabled(false);
                                              }
                                         }
                                  }
                                else  {
                                        StartStatusBar("validating the media stream for "+Streaming_Engines.getSelectedItem().toString()+" streaming engine....");
                                        int IsValid=10;
                                         if(Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("RTSP")) {
                                          IsValid=objRTSP.RTSP_Validate(DAVSServer.RemotrDirectory.concat(MediaStreamName),0);
                                         } else if(Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("PULSE")) {
                                         IsValid=objPulse.P2P_Validate(DAVSServer.RemotrDirectory.concat(MediaStreamName));
                                         } else {
                                          IsValid=objLive555.Live555_Validate(DAVSServer.RemotrDirectory.concat(MediaStreamName),0);
                                         }
                                         if(IsValid==0) {
                                             StopStatusBar();
                                             int result= JOptionPane.showOptionDialog(null,"Media stream has been successfuly validated.\n"+
                                               "Do you want to import into "+Streaming_Engines.getSelectedItem().toString().trim()+" streaming engine.","DAVS Message",
                                               JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                                            if(result==JOptionPane.YES_OPTION) {
                                                 Take_Snapshot.setSelected(false);
                                                 Frame_Import.setEnabled(true);
                                                 Frame_Validate.setSelected(false);
                                                 Frame_Command_Group.setSelected(Frame_Import.getModel(), true);
                                                 Menue_Import.setEnabled(true);
                                                 Streaming_Engines.setEnabled(false);
                                                 Streaming_Type.setEnabled(true);
                                            }
                                         if (result==JOptionPane.NO_OPTION) {
                                             Media_File_Path.setText("");
                                             Take_Snapshot.setSelected(false);
                                             Frame_Command_Group.setSelected(Frame_Validate.getModel(),false);
                                             Menue_Command_Group.setSelected(Menue_Validate.getModel(),false);
                                             Streaming_Type.setEnabled(false);
                                         }
                                       }
                                      else { // if file needs some transcoding.
                                             StopStatusBar();
                                             int ImportOption=JOptionPane.showOptionDialog(null,"Media stream need some transcoding.\n"+
                                                                                         "If you want to import into the streaming engine then set the parameters.","DAVS Message",
                                                                                         JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                                              if(ImportOption==JOptionPane.YES_OPTION) {
                                                   NeedsTranscoding=true;
                                                   Frame_Import.setEnabled(true);
                                                   Menue_Import.setEnabled(true);
                                                   Video_Codec.setEnabled(true);
                                                   Audio_Codec.setEnabled(true);
                                                   Frame_Validate.setSelected(false);
                                                   Frame_Command_Group.setSelected(Frame_Import.getModel(), true);
                                                   BitRate.setEnabled(true);
                                                   Streaming_Type.setEnabled(true);
                                                 }
                                              if(ImportOption==JOptionPane.NO_OPTION) {
                                                 NeedsTranscoding=false;
                                                 Video_Codec.setEnabled(false);
                                                 Audio_Codec.setEnabled(false);
                                                 BitRate.setEnabled(false);
                                                 Streaming_Type.setEnabled(false);
                                              }
                                         }
                                     }
                                 }
                             }
                           else {
                             StopStatusBar();
                             JOptionPane.showMessageDialog(null,"There is some problem in validating the media stream for "+Streaming_Engines.getSelectedItem().toString()
                                                       +" streaming engine.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                                      Media_File_Path.setText("");
                                      Take_Snapshot.setSelected(false);
                                      Frame_Validate.setSelected(false);
                                      Menue_Command_Group.setSelected(Menue_Validate.getModel(),false);
                                      Streaming_Type.setEnabled(false);
                                      Streaming_Engines.setSelectedIndex(0);
                                      Streaming_Engines.setEnabled(false);
                          }
                         }
                         }
                        }
                      else if (Menue_Import.isSelected() || Frame_Import.isSelected() )
                      {
                                               
                              if(Streaming_Type.getSelectedIndex()==0 || Streaming_Type.getSelectedItem().toString().equalsIgnoreCase(" ")) {
                                  JOptionPane.showMessageDialog(null,"Please select the Streaming type.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                                }
                                else if(Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Live Broadcast")
                                     || Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Broadcast Like")) {

                                    if (Streaming_Engines.getSelectedItem().toString().equalsIgnoreCase("LIVE555")) {
                                       JOptionPane.showMessageDialog(null,Streaming_Engines.getSelectedItem().toString()+" does not support "+
                                             Streaming_Type.getSelectedItem().toString()+" streaming. \nPlease select "
                                               +" another streaming type.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else {
                                        if(IsSDP) {
                                         StartStatusBar("Importing media stream into the "+Streaming_Engines.getSelectedItem().toString().trim()+" streaming engine ...");
                                         if(Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Live Broadcast")) {
                                             objRPC_Result = objRTSP.RTSP_Import(1, DAVSServer.RemotrDirectory.concat(MediaStreamName), 1, "", "");
                                         } else if (Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Broadcast Like")) {
                                            objRPC_Result = objRTSP.RTSP_Import(2, DAVSServer.RemotrDirectory.concat(MediaStreamName), 1, "", "");
                                         }

                                           if(objRPC_Result.ExitStatus==0) {
                                             
                                             String SDPURI=new String(objRPC_Result.CommandOutPut);
                                             String URI=SDPURI.substring(0, SDPURI.lastIndexOf("'")).trim();
                                             MediaStreamName=URI.substring(URI.lastIndexOf("/")+1,URI.length());
                                             LiveDescription=(String) JOptionPane.showInputDialog(null,"Enter the description","DAVS Message",JOptionPane.PLAIN_MESSAGE,null,null,null);
                                             int IsStoreinDAVSServer=10;
                                                  if(Streaming_Type.getSelectedItem().toString().trim().equalsIgnoreCase("Broadcast Like") ||
                                                     Streaming_Type.getSelectedItem().toString().trim().equalsIgnoreCase("Live Broadcast")) {
                                                     IsStoreinDAVSServer=objDAVSServer.DAVS_Store_Broadcast(MediaStreamName,LiveDescription);
                                                  }

                                                 if(IsStoreinDAVSServer==0)
                                                 {
                                                     StopStatusBar();
                                                     JOptionPane.showMessageDialog(null,"SDP file has been successfuly imported into the streaming engine.\n"+
                                                                                         "Now you can access the stream with URI:\n"+URI.trim(),"DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                     GetLiveStreams();
                                                     Media_File_Path.setText("");
                                                     Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                     Menue_Command_Group.setSelected(Menue_Validate.getModel(), true);
                                                     Frame_Validate.setSelected(true);
                                                     Menue_Validate.setSelected(true);
                                                     Streaming_Type.setSelectedIndex(0);
                                                     Streaming_Engines.setSelectedIndex(0);
                                                     Streaming_Engines.setEnabled(false);
                                                     Video_Codec.setSelectedIndex(0);
                                                     Streaming_Type.setEnabled(false);
                                                     Audio_Codec.setEnabled(false);
                                                     Video_Codec.setEnabled(false);
                                                     BitRate.setEnabled(false);
                                                     Frame_Validate.setEnabled(true);
                                                     Frame_Import.setEnabled(false);

                                                   }
                                                  else
                                                  {
                                                       StopStatusBar();
                                                       JOptionPane.showMessageDialog(null,"There is some problem in importing the media stream into the "+
                                                                             Streaming_Engines.getSelectedItem()+" streaming engine.","DAVS Message",JOptionPane.ERROR_MESSAGE);

                                                         Media_File_Path.setText("");
                                                         Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                         Streaming_Type.setSelectedIndex(0);
                                                         BitRate.setSelectedIndex(0);
                                                         Audio_Codec.setSelectedIndex(0);
                                                         Streaming_Engines.setSelectedIndex(0);
                                                         Streaming_Engines.setEnabled(false);
                                                         Video_Codec.setSelectedIndex(0);
                                                         Streaming_Type.setEnabled(false);
                                                         Audio_Codec.setEnabled(false);
                                                         Frame_Validate.setSelected(true);
                                                         Menue_Validate.setSelected(true);
                                                         Video_Codec.setEnabled(false);
                                                         BitRate.setEnabled(false);
                                                  }
                                         }
                                        else {
                                             StopStatusBar();
                                             JOptionPane.showMessageDialog(null,"There is some problem in during importing the media stream.",
                                                     "DAVS Error",JOptionPane.ERROR_MESSAGE);

                                             Media_File_Path.setText("");
                                             Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                             Streaming_Type.setSelectedIndex(0);
                                             BitRate.setSelectedIndex(0);
                                             Audio_Codec.setSelectedIndex(0);
                                             Streaming_Engines.setSelectedIndex(0);
                                             Streaming_Engines.setEnabled(false);
                                             Video_Codec.setSelectedIndex(0);
                                             Streaming_Type.setEnabled(false);
                                             Audio_Codec.setEnabled(false);
                                             Video_Codec.setEnabled(false);
                                             BitRate.setEnabled(false);
                                          }
                                        }
                                 else  {
                                            if(Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("RTSP"))
                                            {
                                                if(Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Live Broadcast"))
                                                {
                                                    if(NeedsTranscoding)
                                                    {
                                                     if(Video_Codec.getSelectedIndex()==0 ||  Audio_Codec.getSelectedIndex()==0
                                                             || BitRate.getSelectedIndex()==0)
                                                          {
                                                         JOptionPane.showMessageDialog(null,"To import a vidoe on DAVS server you have to " +
                                                         "set all the parameters for transcoding.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                         }
                                                     else {

                                                               String VideoCodec = Video_Codec.getSelectedItem().toString();
                                                               String AudioCodec = Audio_Codec.getSelectedItem().toString();
                                                               String Bit_Rate   = BitRate.getSelectedItem().toString();

                                                               StartStatusBar("Importing media stream into the "+Streaming_Engines.getSelectedItem().toString()+" streaming engine");
                                                               objRPC_Result=objRTSP.RTSP_Import(1,DAVSServer.RemotrDirectory.concat(MediaStreamName),Integer.parseInt(Bit_Rate),VideoCodec,AudioCodec);
                                                     }
                                                } else {
                                                 objRPC_Result = objRTSP.RTSP_Import(1, DAVSServer.RemotrDirectory.concat(MediaStreamName), 1, "", "");
                                                }
                                            } else if (Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Broadcast Like")) {
                                                if(NeedsTranscoding) {
                                                    if(Video_Codec.getSelectedIndex()==0 || Audio_Codec.getSelectedIndex()==0 || BitRate.getSelectedIndex()==0) {
                                                         JOptionPane.showMessageDialog(null,"To import a vidoe on DAVS server you have to " +
                                                         "set all the parameters for transcoding.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                     else {
                                                               String VideoCodec = Video_Codec.getSelectedItem().toString();
                                                               String AudioCodec = Audio_Codec.getSelectedItem().toString();
                                                               String Bit_Rate   = BitRate.getSelectedItem().toString();

                                                               StartStatusBar("Importing media stream into the "+Streaming_Engines.getSelectedItem().toString()+" streaming engine");
                                                               objRPC_Result=objRTSP.RTSP_Import(2,DAVSServer.RemotrDirectory.concat(MediaStreamName),Integer.parseInt(Bit_Rate),VideoCodec,AudioCodec);
                                                     }
                                                } else {
                                                 objRPC_Result = objRTSP.RTSP_Import(2, DAVSServer.RemotrDirectory.concat(MediaStreamName), 1, "", "");
                                                }
                                             }
                                            if(objRPC_Result.ExitStatus==0) {

                                             URL=new String(objRPC_Result.CommandOutPut);
                                             String URI =URL.substring(0, URL.lastIndexOf("'")+1);
                                             MediaStreamName=URI.substring(URI.lastIndexOf("/")+1,URI.lastIndexOf("'")-1);
                                             LiveDescription=(String) JOptionPane.showInputDialog(null,"Enter the description","DAVS Message",
                                                                                          JOptionPane.PLAIN_MESSAGE,null,null,null);
                                             if(LiveDescription.length()<=0) {
                                                      LiveDescription="";
                                               }
                                             if(objDAVSServer.DAVS_Store_Broadcast(MediaStreamName,LiveDescription)==0) {
                                                     StopStatusBar();
                                                     JOptionPane.showMessageDialog(null,"Media stream has been successfuly imported into the streaming engine.\n"+
                                                                                        "Now you can access the stream with URI:\n"+URI.trim(),"DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                     GetLiveStreams();
                                                     Media_File_Path.setText("");
                                                     Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                     Menue_Command_Group.setSelected(Menue_Validate.getModel(), true);
                                                     Frame_Validate.setSelected(true);
                                                     Menue_Validate.setSelected(true);
                                                     Audio_Codec.setSelectedIndex(0);
                                                     BitRate.setSelectedIndex(0);
                                                     Streaming_Type.setSelectedIndex(0);
                                                     Streaming_Engines.setSelectedIndex(0);
                                                     Streaming_Engines.setEnabled(false);
                                                     Video_Codec.setSelectedIndex(0);
                                                     Streaming_Type.setEnabled(false);
                                                     Audio_Codec.setEnabled(false);
                                                     Video_Codec.setEnabled(false);
                                                     BitRate.setEnabled(false);
                                                     Frame_Validate.setEnabled(true);
                                                     Frame_Import.setEnabled(false);

                                                   }
                                                  else
                                                  {
                                                         StopStatusBar();
                                                         JOptionPane.showMessageDialog(null,"There is some problem in importing the media stream into the "+
                                                                             Streaming_Engines.getSelectedItem().toString()+" streaming engine.","DAVS Message",JOptionPane.ERROR_MESSAGE);

                                                         Media_File_Path.setText("");
                                                         Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                         Menue_Command_Group.setSelected(Menue_Validate.getModel(), true);
                                                         Frame_Validate.setSelected(true);
                                                         Menue_Validate.setSelected(true);
                                                         Audio_Codec.setSelectedIndex(0);
                                                         BitRate.setSelectedIndex(0);
                                                         Streaming_Type.setSelectedIndex(0);
                                                         Streaming_Engines.setSelectedIndex(0);
                                                         Streaming_Engines.setEnabled(false);
                                                         Video_Codec.setSelectedIndex(0);
                                                         Streaming_Type.setEnabled(false);
                                                         Audio_Codec.setEnabled(false);
                                                         Video_Codec.setEnabled(false);
                                                         BitRate.setEnabled(false);
                                                         Frame_Validate.setEnabled(true);
                                                         Frame_Import.setEnabled(false);
                                                  }
                                             }
                                        else {
                                                 StopStatusBar();
                                                 JOptionPane.showMessageDialog(null,"There is some problem during importing the media stream into the streaming engine."
                                                                               ,"DAVS Error",JOptionPane.ERROR_MESSAGE);

                                                     Media_File_Path.setText("");
                                                     Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                     Menue_Command_Group.setSelected(Menue_Validate.getModel(), true);
                                                     Frame_Validate.setSelected(true);
                                                     Menue_Validate.setSelected(true);
                                                     Audio_Codec.setSelectedIndex(0);
                                                     BitRate.setSelectedIndex(0);
                                                     Streaming_Type.setSelectedIndex(0);
                                                     Streaming_Engines.setSelectedIndex(0);
                                                     Streaming_Engines.setEnabled(false);
                                                     Video_Codec.setSelectedIndex(0);
                                                     Streaming_Type.setEnabled(false);
                                                     Audio_Codec.setEnabled(false);
                                                     Video_Codec.setEnabled(false);
                                                     BitRate.setEnabled(false);
                                                     Frame_Validate.setEnabled(true);
                                                     Frame_Import.setEnabled(false);
                                          }
                                        } // end if RTSP streaming engine
                                       else if (Streaming_Engines.getSelectedItem().toString().equalsIgnoreCase("Pulse"))
                                       {
                                             if(Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Live Broadcast")) {
                                                JOptionPane.showMessageDialog(null,"PULSE streaming engine does not support Live Broadcast streaming.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);

                                             }
                                             else
                                             {
                                                 StartStatusBar("Importing media stream into the "+Streaming_Engines.getSelectedItem().toString()+" streaming engine");
                                                 if(NeedsTranscoding)
                                                    {
                                                     if(Video_Codec.getSelectedIndex()==0 ||  Audio_Codec.getSelectedIndex()==0
                                                             || BitRate.getSelectedIndex()==0)
                                                          {
                                                         JOptionPane.showMessageDialog(null,"To import a vidoe on DAVS server you have to " +
                                                         "set all the parameters for transcoding.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                         }
                                                     else {
                                                               String VideoCodec = Video_Codec.getSelectedItem().toString();
                                                               String AudioCodec = Audio_Codec.getSelectedItem().toString();
                                                               String Bit_Rate   = BitRate.getSelectedItem().toString();

                                                               StartStatusBar("Importing media stream into the "+Streaming_Engines.getSelectedItem().toString()+" streaming engine");
                                                               objRPC_Result=objPulse.P2P_Import(DAVSServer.RemotrDirectory.concat(MediaStreamName),Integer.parseInt(Bit_Rate),VideoCodec,AudioCodec);
                                                     }
                                                } else {
                                                 objRPC_Result = objPulse.P2P_Import(DAVSServer.RemotrDirectory.concat(MediaStreamName), 1, "", "");
                                                }
                                            if(objRPC_Result.ExitStatus==0)
                                            {
                                             String Message=new String(objRPC_Result.CommandOutPut);
                                             String StreamID=Message.trim();
                                             StreamID=StreamID.substring(StreamID.indexOf("'")+1,StreamID.lastIndexOf("'"));
                                                                                               
                                             String PulseDescription="";
                                             PulseDescription=(String) JOptionPane.showInputDialog(null,"Enter the description","DAVS Message",
                                                                                          JOptionPane.PLAIN_MESSAGE,null,null,null);
                                             if(PulseDescription.length()<=0) {
                                                      PulseDescription="";
                                               }
                                             if(objDAVSServer.DAVS_Store_Broadcast(StreamID,PulseDescription)==0) {
                                                     StopStatusBar();
                                                     JOptionPane.showMessageDialog(null,"Message: Media stream has been succesfully imported into the P2P PULSE streaming engine.",
                                                             "DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                     GetLiveStreams();
                                                     Media_File_Path.setText("");
                                                     Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                     Menue_Command_Group.setSelected(Menue_Validate.getModel(), true);
                                                     Frame_Validate.setSelected(true);
                                                     Menue_Validate.setSelected(true);
                                                     Audio_Codec.setSelectedIndex(0);
                                                     BitRate.setSelectedIndex(0);
                                                     Streaming_Type.setSelectedIndex(0);
                                                     Streaming_Engines.setSelectedIndex(0);
                                                     Streaming_Engines.setEnabled(false);
                                                     Video_Codec.setSelectedIndex(0);
                                                     Streaming_Type.setEnabled(false);
                                                     Audio_Codec.setEnabled(false);
                                                     Video_Codec.setEnabled(false);
                                                     BitRate.setEnabled(false);
                                                     Frame_Validate.setEnabled(true);
                                                     Frame_Import.setEnabled(false);

                                                   }
                                                  else
                                                  {
                                                         StopStatusBar();
                                                         JOptionPane.showMessageDialog(null,"There is some problem in importing the media stream into the "+
                                                                             Streaming_Engines.getSelectedItem().toString()+" streaming engine.","DAVS Message",JOptionPane.ERROR_MESSAGE);

                                                         Media_File_Path.setText("");
                                                         Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                         Menue_Command_Group.setSelected(Menue_Validate.getModel(), true);
                                                         Frame_Validate.setSelected(true);
                                                         Menue_Validate.setSelected(true);
                                                         Audio_Codec.setSelectedIndex(0);
                                                         BitRate.setSelectedIndex(0);
                                                         Streaming_Type.setSelectedIndex(0);
                                                         Streaming_Engines.setSelectedIndex(0);
                                                         Streaming_Engines.setEnabled(false);
                                                         Video_Codec.setSelectedIndex(0);
                                                         Streaming_Type.setEnabled(false);
                                                         Audio_Codec.setEnabled(false);
                                                         Video_Codec.setEnabled(false);
                                                         BitRate.setEnabled(false);
                                                         Frame_Validate.setEnabled(true);
                                                         Frame_Import.setEnabled(false);
                                                  }
                                              }
                                              else
                                              {
                                                         StopStatusBar();
                                                         JOptionPane.showMessageDialog(null,"There is some problem in importing the media stream into the "+
                                                                             Streaming_Engines.getSelectedItem().toString()+" streaming engine.","DAVS Message",JOptionPane.ERROR_MESSAGE);

                                                         Media_File_Path.setText("");
                                                         Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                         Menue_Command_Group.setSelected(Menue_Validate.getModel(), true);
                                                         Frame_Validate.setSelected(true);
                                                         Menue_Validate.setSelected(true);
                                                         Audio_Codec.setSelectedIndex(0);
                                                         BitRate.setSelectedIndex(0);
                                                         Streaming_Type.setSelectedIndex(0);
                                                         Streaming_Engines.setSelectedIndex(0);
                                                         Streaming_Engines.setEnabled(false);
                                                         Video_Codec.setSelectedIndex(0);
                                                         Streaming_Type.setEnabled(false);
                                                         Audio_Codec.setEnabled(false);
                                                         Video_Codec.setEnabled(false);
                                                         BitRate.setEnabled(false);
                                                         Frame_Validate.setEnabled(true);
                                                         Frame_Import.setEnabled(false);


                                                }
                                             }
                                          } // end if Pulse Streaming engine
                                       } // end else if file is not SDP.
                                    }  // end if streaming engine is RTSP.
                               } // end if streaming type is live streaming.
                              else if(Streaming_Type.getSelectedItem().toString().equalsIgnoreCase("Video on Demand")) {
                                  if(IsSDP) {
                                      JOptionPane.showMessageDialog(null, "DAVS server accepts SDP file only for Live broadcast or broadcast streaming.",
                                              "DAVS Message",JOptionPane.ERROR_MESSAGE);
                                     } else if (Streaming_Engines.getSelectedItem().toString().equalsIgnoreCase("PULSE")) {
                                      JOptionPane.showMessageDialog(null, "DAVS server does not support Video on demand for Pulse stream.",
                                      "DAVS Message",JOptionPane.ERROR_MESSAGE);
                                     }
                                  else {
                                     if(NeedsTranscoding) {
                                        if(Video_Codec.getSelectedIndex()==0 || Audio_Codec.getSelectedIndex()==0
                                                  || BitRate.getSelectedIndex()==0) {
                                            JOptionPane.showMessageDialog(null,"To import a vidoe on DAVS server you have to " +
                                                    "set all the parameters for transcoding.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        else {
                                                String VideoCodec = Video_Codec.getSelectedItem().toString();
                                                String AudioCodec = Audio_Codec.getSelectedItem().toString();
                                                String Bit_Rate   = BitRate.getSelectedItem().toString();

                                                StartStatusBar("Importing media stream into the "+Streaming_Engines.getSelectedItem().toString()+" streaming engine");
                                                if (Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("RTSP")) {
                                                   objRPC_Result=objRTSP.RTSP_Import(3,DAVSServer.RemotrDirectory.concat(MediaStreamName),Integer.parseInt(Bit_Rate),VideoCodec,AudioCodec);
                                                 } else {
                                                   objRPC_Result=objLive555.Live555_Import(3,DAVSServer.RemotrDirectory.concat(MediaStreamName),Integer.parseInt(Bit_Rate),VideoCodec,AudioCodec);
                                                 }
                                                if(objRPC_Result.ExitStatus==0) {
                                                     URL=new String(objRPC_Result.CommandOutPut);
                                                     
                                                     String ImageURL="";
                                                     String StreamID=URL.substring(URL.lastIndexOf("/")+1,URL.lastIndexOf("'")).trim();
                                                     String StreamName=MediaStreamName.substring(0,MediaStreamName.lastIndexOf("."));
                                                     VodDescription=(String) JOptionPane.showInputDialog(null,"Enter the description","DAVS Message",JOptionPane.PLAIN_MESSAGE,null,null,null);

                                                     if(VodDescription.length()<=0) {
                                                            VodDescription="";
                                                          }
                                                     if(Take_Snapshot.isSelected()) {
                                                           ImageURL=DAVSServer.ImageDirectory.concat(StreamName+".jpeg");
                                                      }
                                                      int IsStored=objDAVSServer.DAVS_Store_VOD(MediaStreamName,VodDescription,StreamID,ImageURL,
                                                              URL.substring(URL.indexOf("'")+1, URL.lastIndexOf("'")));
                                                     if(IsStored==0) {
                                                          StopStatusBar();
                                                          JOptionPane.showMessageDialog(null,"Media stream has been successfuly imported into the streaming engine.\n"+
                                                                                         "Now you can access the stream with URI:\n"+URL.trim());
                                                          GetVODStreams();
                                                          Menue_Import.setSelected(false);
                                                          Frame_Import.setSelected(false);
                                                          Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                          Streaming_Type.setSelectedIndex(0);
                                                          BitRate.setSelectedIndex(0);
                                                          Audio_Codec.setSelectedIndex(0);
                                                          Video_Codec.setSelectedIndex(0);
                                                          Audio_Codec.setEnabled(false);
                                                          Video_Codec.setEnabled(false);
                                                          BitRate.setEnabled(false);
                                                          Streaming_Type.setEnabled(false);
                                                          Media_File_Path.setText("");
                                                    }
                                                    else
                                                    {
                                                              StopStatusBar();
                                                              JOptionPane.showMessageDialog(null,"There is some problem in importing into the "
                                                                      +Streaming_Engines.getSelectedItem().toString().trim()+" streaming engine.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                                                              Streaming_Type.setSelectedIndex(0);
                                                              Streaming_Type.setEnabled(false);
                                                              Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                              Media_File_Path.setText("");
                                                              Streaming_Engines.setSelectedIndex(0);
                                                              Streaming_Engines.setEnabled(false);
                                                    }
                                                 }
                                                else
                                                {
                                                    StopStatusBar();
                                                    JOptionPane.showMessageDialog(null,"There is some problem in importing into the streaming engine."
                                                            ,"DAVS Message",JOptionPane.ERROR_MESSAGE);
                                                    Streaming_Type.setSelectedIndex(0);
                                                    Streaming_Type.setEnabled(false);
                                                    Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                    Media_File_Path.setText("");
                                                    Streaming_Engines.setSelectedIndex(0);
                                                    Streaming_Engines.setEnabled(false);
                                                }
                                             }
                                          }
                                         else // does not need transcoding
                                            {
                                                      StartStatusBar("Importing into the "+Streaming_Engines.getSelectedItem()+" streaming engine");
                                                      if(Streaming_Engines.getSelectedItem().toString().trim().equalsIgnoreCase("RTSP")) {
                                                        objRPC_Result=objRTSP.RTSP_Import(3,DAVSServer.RemotrDirectory.concat(MediaStreamName),1, "", "");
                                                      } else if(Streaming_Engines.getSelectedItem().toString().equalsIgnoreCase("LIVE555")){
                                                        objRPC_Result=objLive555.Live555_Import(3,DAVSServer.RemotrDirectory.concat(MediaStreamName),1, "", "");
                                                      }
                                                      if(objRPC_Result.ExitStatus==0)
                                                      {
                                                          URL=new String(objRPC_Result.CommandOutPut);
                                                          String ImageURL="";
                                                          String StreamID=URL.substring(URL.lastIndexOf("/")+1,URL.lastIndexOf("'")).trim();
                                                          String StreamName=MediaStreamName.substring(0,MediaStreamName.lastIndexOf("."));

                                                          VodDescription=(String) JOptionPane.showInputDialog(this,"Enter the description",
                                                                                   "DAVS Message",JOptionPane.PLAIN_MESSAGE,null,null,null);

                                                          if(VodDescription.length()<=0) {
                                                              VodDescription="";
                                                          }
                                                          if(Take_Snapshot.isSelected()) {
                                                              ImageURL=DAVSServer.ImageDirectory.concat(StreamName+".jpeg");
                                                          }
                                                          int IsImported=objDAVSServer.DAVS_Store_VOD(MediaStreamName,VodDescription,StreamID,ImageURL,
                                                                                            URL.substring(URL.indexOf("'")+1, URL.lastIndexOf("'")));
                                                       
                                                          if(IsImported==0)
                                                          {
                                                               StopStatusBar();
                                                               JOptionPane.showMessageDialog(null,"Media stream has been successfuly imported into the "+Streaming_Engines.getSelectedItem()
                                                                                        +"streaming engine.\nNow you can access the stream with following URI.\n"
                                                                                         +URL.trim(),"DAVS Message",JOptionPane.INFORMATION_MESSAGE);

                                                                  GetVODStreams();
                                                                  Streaming_Type.setSelectedIndex(0);
                                                                  Streaming_Type.setEnabled(false);
                                                                  Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                                  Media_File_Path.setText("");
                                                                  Streaming_Engines.setSelectedIndex(0);
                                                                  Streaming_Engines.setEnabled(false);
                                                                  Frame_Import.setEnabled(false);
                                                                  Menue_Import.setEnabled(false);
                                                          }
                                                          else
                                                          {
                                                              StopStatusBar();
                                                              JOptionPane.showMessageDialog(null,"There is some problem during importing into the "
                                                                      +Streaming_Engines.getSelectedItem()+" streaming engine.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                                                              Streaming_Type.setSelectedIndex(0);
                                                              Streaming_Type.setEnabled(false);
                                                              Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                              Media_File_Path.setText("");
                                                              Streaming_Engines.setSelectedIndex(0);
                                                              Streaming_Engines.setEnabled(false);
                                                          }
                                                      }
                                                      else
                                                      {
                                                          StopStatusBar();
                                                          JOptionPane.showMessageDialog(null,"There is some problem during importing."
                                                                  +Streaming_Engines.getSelectedItem().toString()+" streaming engine","DAVS Message",JOptionPane.ERROR_MESSAGE);
                                                          Streaming_Type.setSelectedIndex(0);
                                                          Streaming_Type.setEnabled(false);
                                                          Frame_Command_Group.setSelected(Frame_Validate.getModel(), true);
                                                          Media_File_Path.setText("");
                                                          Streaming_Engines.setSelectedIndex(0);
                                                          Streaming_Engines.setEnabled(false);

                                                      }
                                                   }
                                                }
                                             }
                                           }
                                       else  {
                            JOptionPane.showMessageDialog(null, "Please select the command.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                        }
                  }
    }//GEN-LAST:event_Run_CommandActionPerformed


    private void Start_Live_Stream () {

            int TotalStreams=b_streams.getModel().getSize();
            if(TotalStreams>0)
            {
                     if(b_streams.isSelectionEmpty())
                     {
                         JOptionPane.showMessageDialog(null,"Please select the broadcast live stream"
                                 +" from the DAVS server.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                     }
                    else
                    {
                                String LiveStreamID = (String) b_streams.getSelectedValue();
                                String Is_SDP_Stream =LiveStreamID.substring(LiveStreamID.lastIndexOf(".")+1,LiveStreamID.length());
                                
                                if(Is_SDP_Stream.equalsIgnoreCase("sdp") || Is_SDP_Stream.equalsIgnoreCase("SDP"))
                                {
                                    JOptionPane.showMessageDialog(null," DAVS does not allow to start SDP stream. "+
                                            "You can only recieve the live stream.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                                }
                                else {
                                      if(objDAVSEngines.StreamAvailable(LiveStreamID.trim())==0)
                                      {

                                        StartStatusBar("Starting the broadcast live stream ....");
                                        objRPC_Result=objRTSP.RTSP_Live_Start(LiveStreamID);
                                        if(objRPC_Result.ExitStatus==0)
                                        {
                                            String LiveURL = new String(objRPC_Result.CommandOutPut);
                                            String BroadcastSDPFile=LiveURL.substring(LiveURL.lastIndexOf("/")+1,LiveURL.lastIndexOf("'"));
                                            int IsInserted=objDAVSServer.DAVS_Store_Broadcast(BroadcastSDPFile,"This stream is currently active. you can view the stream.");
                                            if( IsInserted==0){
                                                StopStatusBar();
                                                JOptionPane.showMessageDialog(null,"Media stream has been started. Now you can access the live stream using this URI:\n"+
                                                        LiveURL.trim(),"DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                GetLiveStreams();
                                            }
                                            else {
                                                StopStatusBar();
                                                JOptionPane.showMessageDialog(null,"There is some problem is DAVS server.","DAVS Message"
                                                        ,JOptionPane.ERROR_MESSAGE);

                                            }
                                        }
                                        else {
                                            StopStatusBar();
                                            JOptionPane.showMessageDialog(null,"There is some problem in starting the live stream."
                                                    ,"DAVS Message",JOptionPane.ERROR_MESSAGE);
                                            Frame_Command_Group.setSelected(Frame_Validate.getModel(),true);
                                          }
                                        }// end if stream is live stream
                                      else {
                                            JOptionPane.showMessageDialog(null,"This is not a Live broadcast stream.\nPlesae select live broadcast stream from the DAVS server."
                                             ,"DAVS Message",JOptionPane.ERROR_MESSAGE);

                                        }
                                      }
                                }
                         }
        else {
               JOptionPane.showMessageDialog(null,"No broadcast live streams are available on the DAVS server."
                       ,"DAVS Message",JOptionPane.INFORMATION_MESSAGE);
            }
    }

    private void GetVODStreams ()  {
             String Vod_Streams = objDAVSServer.DAVS_Get_VODStreams();
            if(Vod_Streams.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "No VOD streams are available on the DAVS server.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
            StringTokenizer StreamID = new StringTokenizer(Vod_Streams, "=");
            obj_vod.clear();
              while(StreamID.hasMoreTokens()){
                 obj_vod.addElement(StreamID.nextToken());
               }
          }
  }

    private void GetLiveStreams () {
        
                String Live_Streams = objDAVSServer.DAVS_Get_LiveStreams();
                if (Live_Streams.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "No live streams are available on the DAVS server", "DAVS Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringTokenizer BroadcastStreams = new StringTokenizer(Live_Streams, "=");
                    obj_live.clear();
                    while (BroadcastStreams.hasMoreTokens()) {
                        obj_live.addElement(BroadcastStreams.nextToken());
                    }
                  }
 }

    private void Stop_Live_Stream () {
           int TotalStream=b_streams.getModel().getSize();
           if(TotalStream>0) {
               if(b_streams.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null,"Please select the broadcast live stream"
                                 +" from the DAVS server.","DAVS Message",JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                   String LiveStream=(String)b_streams.getSelectedValue();
                   String Is_SDP_File = LiveStream.substring(LiveStream.lastIndexOf(".")+1,LiveStream.length());
                   if(Is_SDP_File.equalsIgnoreCase("sdp") || Is_SDP_File.equalsIgnoreCase("SDP"))
                    {
                      JOptionPane.showMessageDialog(null,"DAVS server does not allow you to stop SDP media stream."
                              ,"DAVS Message",JOptionPane.ERROR_MESSAGE);
                    }
                   else
                       {
                          if(objDAVSEngines.StreamAvailable(LiveStream)==0) 
                          {
                                int IsStreamStop = objRTSP.RTSP_Live_Stop(LiveStream);
                                if (IsStreamStop == 0) {
                                    String SDPFile=LiveStream.substring(0,LiveStream.lastIndexOf(".")).concat(".sdp");
                                    int IsRemoved=objDAVSServer.DAVS_RemoveLiveStreams(SDPFile);
                                    if(IsRemoved==0) {
                                       JOptionPane.showMessageDialog(null, "Live stream has been stopped successfuly.", "DAVS Message", JOptionPane.ERROR_MESSAGE);
                                       GetLiveStreams();
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null,"There is some problem in stopping the live stream."
                                                ,"DAVS Message",JOptionPane.ERROR_MESSAGE);
                                         }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Live stream is not started yet.", "DAVS Message", JOptionPane.ERROR_MESSAGE);
                                }
                          } else {
                          JOptionPane.showMessageDialog(null, "This is not a Live stream.\n Please select the Live stream fromt the DAVS server.", "DAVS Message", JOptionPane.ERROR_MESSAGE);
                          }
                       }
                 }
            }
           else {
              JOptionPane.showMessageDialog(null,"No broadcast live streams are available on the DAVS server."
                       ,"DAVS Message",JOptionPane.INFORMATION_MESSAGE);
           }
  }


    private void  P2P_Start () {

            int TotalStreams=b_streams.getModel().getSize();
            if(TotalStreams>0)
            {
                     if(b_streams.isSelectionEmpty())
                     {
                         JOptionPane.showMessageDialog(null,"Please select the PULSE stream from the DAVS server.",
                                 "DAVS Message",JOptionPane.ERROR_MESSAGE);
                     }
                    else
                    {
                                String P2PStreamID = (String) b_streams.getSelectedValue();
                                String Is_SDP_Stream =P2PStreamID.substring(P2PStreamID.lastIndexOf(".")+1,P2PStreamID.length());

                                if(Is_SDP_Stream.equalsIgnoreCase("sdp") || Is_SDP_Stream.equalsIgnoreCase("SDP"))
                                {
                                    JOptionPane.showMessageDialog(null," DAVS does not support SDP file in Pulse streaming engine. "
                                            ,"DAVS Message",JOptionPane.ERROR_MESSAGE);
                                }
                                else {
                                       if (objDAVSEngines.StreamAvailable(P2PStreamID)==2)
                                       {
                                          StartStatusBar("Starting the P2P Pulse stream in source mode ....");
                                          int IsStarted=objPulse.P2P_Start(P2PStreamID);
                                        if(IsStarted==0)
                                        {
                                              StopStatusBar();
                                              JOptionPane.showMessageDialog(null,"Pulse stream has been started successfuly in source mode.\n"+
                                                      "Now clients can recieve the streams.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                                                //GetLiveStreams();
                                         }
                                         else {
                                                StopStatusBar();
                                                JOptionPane.showMessageDialog(null,"There is some problem is DAVS server.","DAVS Message"
                                                        ,JOptionPane.ERROR_MESSAGE);

                                            }
                                       } // end if stream exit in the P2P PULSE streaming engine.
                                       else {
                                         JOptionPane.showMessageDialog(null,"This is not a Pulse stream.\n Please choose the Pulse stream from the DAVS server."
                                           ,"DAVS Message",JOptionPane.ERROR_MESSAGE);

                                       }
                                     }
                                }
                         } else {
               JOptionPane.showMessageDialog(null,"No media streams are available on the DAVS server."
                ,"DAVS Message",JOptionPane.INFORMATION_MESSAGE);
            }
        }


    private void Stop_P2P(String Mode) {

         int TotalStream=b_streams.getModel().getSize();
           if(TotalStream>0) {
               if(b_streams.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null,"Please select the media stream"
                                 +" from the DAVS server.","DAVS Message",JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                   String P2PStream=(String)b_streams.getSelectedValue();
                   String Is_SDP_File = P2PStream.substring(P2PStream.lastIndexOf(".")+1,P2PStream.length());
                   if(Is_SDP_File.equalsIgnoreCase("sdp") || Is_SDP_File.equalsIgnoreCase("SDP"))
                    {
                      JOptionPane.showMessageDialog(null,"PULSE does not support SDP media stream.\n Please select the PULSE stream from DAVS server."
                              ,"DAVS Message",JOptionPane.ERROR_MESSAGE);
                    }
                   else
                       {
                          if(objDAVSEngines.StreamAvailable(P2PStream)==2)
                          {
                                int IsStreamStop = objPulse.P2P_Stop(P2PStream,Mode);
                                if (IsStreamStop == 0) {
                                       JOptionPane.showMessageDialog(null, "P2P PULSE stream has been stopped successfuly.",
                                               "DAVS Message", JOptionPane.ERROR_MESSAGE);
                                } else {
                               JOptionPane.showMessageDialog(null, "P2P stream is not started yet.", "DAVS Message", JOptionPane.ERROR_MESSAGE);
                                }
                          } else {
                           JOptionPane.showMessageDialog(null, "This is not a PULSE stream.\n Please select the PULSE stream from the DAVS server.",
                             "DAVS Message", JOptionPane.ERROR_MESSAGE);
                          }
                        }
                 }
            }
           else {
              JOptionPane.showMessageDialog(null,"No Media streams are available on the DAVS server."
                       ,"DAVS Message",JOptionPane.INFORMATION_MESSAGE);
           }

    }

    private void Get_Vod_streamsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Get_Vod_streamsMouseClicked
            String Vod_Streams = objDAVSServer.DAVS_Get_VODStreams();
            if(Vod_Streams.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "No VOD streams are available on the DAVS server.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                StringTokenizer StreamID = new StringTokenizer(Vod_Streams, "=");
                obj_vod.clear();
              while(StreamID.hasMoreTokens()){
                 obj_vod.addElement(StreamID.nextToken());
               }
              if(!davs_snapshot.isEnabled()) {
                  davs_snapshot.setEnabled(true);
              }
            }
    }//GEN-LAST:event_Get_Vod_streamsMouseClicked

    private void Get_Broadcast_streamsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Get_Broadcast_streamsMouseClicked

            String Live_Streams = objDAVSServer.DAVS_Get_LiveStreams();
            if(Live_Streams.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null,"No live streams are available on the DAVS server","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
            StringTokenizer BroadcastStreams= new StringTokenizer(Live_Streams,"=");
            obj_live.clear();
            while(BroadcastStreams.hasMoreTokens()) {
                obj_live.addElement(BroadcastStreams.nextToken());
            }
            if(DAVSRole.equalsIgnoreCase("admin")) {
                if(!Frame_Start.isEnabled() ){
                  Frame_Start.setEnabled(true);
                }
                if(!Frame_Stop.isEnabled()) {
                    Frame_Stop.setEnabled(true);
                }
            } else {
                Frame_Start.setEnabled(false);
                Frame_Start.setEnabled(false);
            }
          }

}//GEN-LAST:event_Get_Broadcast_streamsMouseClicked

    private void Frame_ValidateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Frame_ValidateMouseClicked
        // TODO add your handling code here:
        if (evt.getSource()==Frame_Validate){
            if(IsSDP) {
                Take_Snapshot.setEnabled(false);
            }
            else {
                Take_Snapshot.setEnabled(true);
            }
        }
    }//GEN-LAST:event_Frame_ValidateMouseClicked

    private void Play_VODMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Play_VODMouseClicked
        // TODO add your handling code here:
             if(vod_streamslist.isSelectionEmpty()) {
               JOptionPane.showMessageDialog(null,"Please select the VOD stream first.","DAVS Message",JOptionPane.ERROR_MESSAGE);
             }
             else {
                 String StreamID=(String)vod_streamslist.getSelectedValue();
                 String URI=objDAVSServer.DAVS_GetURI(StreamID);
                 objPlayStreams.DAVS_PlayVodStream(URI);
             }
    }//GEN-LAST:event_Play_VODMouseClicked

    private void Remove_VODMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Remove_VODMouseClicked
        // TODO add your handling code here:
        if(DAVSRole.equalsIgnoreCase("admin")) 
        {
         if(vod_streamslist.isSelectionEmpty()) {
               JOptionPane.showMessageDialog(null,"Please select the VOD stream first.","DAVS Message",JOptionPane.ERROR_MESSAGE);
             }
             else {
                   String VODStreamID = (String) vod_streamslist.getSelectedValue();
                    if(objRTSP.RTSP_Deport(VODStreamID)==0)
                    {
                        if(objDAVSServer.DAVS_RemoveVODStreams(VODStreamID)==0)
                        {
                          JOptionPane.showMessageDialog(null,"Stream has been removed from the DAVS server.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                          String VODStreams = objDAVSServer.DAVS_Get_VODStreams();
                          StringTokenizer BroadcastStreams= new StringTokenizer(VODStreams,"=");
                          obj_vod.clear();
                          while(BroadcastStreams.hasMoreTokens()) {
                              obj_vod.addElement(BroadcastStreams.nextToken());
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Stream does not exist in the DAVS server.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                    }
                }
           }
    }//GEN-LAST:event_Remove_VODMouseClicked

    private void MenueCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenueCloseMouseClicked
            
               this.dispose();
               System.exit(0);
            
}//GEN-LAST:event_MenueCloseMouseClicked

    private void Remove_LiveStreamsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Remove_LiveStreamsMouseClicked
       if(evt.getSource()==Remove_LiveStreams) 
       {
           if(DAVSRole.equalsIgnoreCase("admin"))
           {
           if(b_streams.isSelectionEmpty()) {
               JOptionPane.showMessageDialog(null,"Please select the Live stream first.","DAVS Message",JOptionPane.ERROR_MESSAGE);
             }
             else {

                    String StreamID = (String) b_streams.getSelectedValue();
                   if(objRTSP.RTSP_Deport(StreamID)==0)
                    {
                        if(objDAVSServer.DAVS_RemoveLiveStreams(StreamID)==0)
                        {
                          JOptionPane.showMessageDialog(null,"Stream has been removed from the DAVS server.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                          String LiveStreams = objDAVSServer.DAVS_Get_LiveStreams();
                          StringTokenizer BroadcastLiveStreams= new StringTokenizer(LiveStreams,"=");
                          obj_live.clear();
                          while(BroadcastLiveStreams.hasMoreTokens()) {
                              obj_live.addElement(BroadcastLiveStreams.nextToken());
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Stream does not exist in the DAVS server.","DAVS Message",JOptionPane.ERROR_MESSAGE);
                    }
               }
            }
       }
}//GEN-LAST:event_Remove_LiveStreamsMouseClicked

    private void Frame_ValidateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Frame_ValidateItemStateChanged
        // TODO add your handling code here:
        if(evt.getSource()==Frame_Validate) {
            if(Frame_Validate.isSelected()) {
                Streaming_Engines.setSelectedIndex(0);

                Streaming_Engines.setEnabled(true);
                Take_Snapshot.setSelected(false);
                Take_Snapshot.setEnabled(true);
            }
            else {
                Streaming_Engines.setEnabled(false);
                Take_Snapshot.setEnabled(false);
            }
        }
    }//GEN-LAST:event_Frame_ValidateItemStateChanged

    private void Frame_StartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Frame_StartItemStateChanged
        // TODO add your handling code here:
        if(evt.getSource()==Frame_Start) {
            if(Streaming_Engines.isEnabled())  {
                 Streaming_Engines.setEnabled(false);
            }
            if(!Run_Command.isEnabled()) {
                Run_Command.setEnabled(true);
            }
            else {
                
                Streaming_Engines.setEnabled(false);

            }
        }
    }//GEN-LAST:event_Frame_StartItemStateChanged

    private void Frame_StopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Frame_StopItemStateChanged
        // TODO add your handling code here:
        if(evt.getSource()==Frame_Stop) {
            if(Streaming_Engines.isEnabled())  {
                  Streaming_Engines.setEnabled(false);
            }
            if(!Run_Command.isEnabled()) {
                Run_Command.setEnabled(true);
            }
            else {
                 Streaming_Engines.setEnabled(false);

            }
        }
    }//GEN-LAST:event_Frame_StopItemStateChanged

    private void Play_LiveStreamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Play_LiveStreamMouseClicked
    
    if (evt.getSource()==Play_LiveStream)
    {
          String LiveStream=null;
          
        int IsPlay=JOptionPane.showOptionDialog(null,"Do you want to play the Pulse P2P stream."
            ,"DAVS Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
       if(IsPlay==JOptionPane.YES_OPTION) {
              if (b_streams.isSelectionEmpty()) {
                 JOptionPane.showMessageDialog(null,"Please select the Broadcast live stream.");
             }
            else
            {
              if(objDAVSEngines.StreamAvailable(b_streams.getSelectedValue().toString().trim())==2)
               {
                  int IsStarted=objPulse.P2P_PulseStarted(b_streams.getSelectedValue().toString().trim());
                  if(IsStarted==1) {
                      JOptionPane.showMessageDialog(null,"Pulse stream is not yet started.","DAVS Message",JOptionPane.INFORMATION_MESSAGE);
                  } else {
                            try {
                                String P2PPulseStream = b_streams.getSelectedValue().toString().trim();
                                obj.SecurePulseStreamFromDAVS(P2PPulseStream);
                                Thread.sleep(3000);
                                objPulse.P2P_Play(P2PPulseStream);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(DAVS_Client.class.getName()).log(Level.SEVERE, null, ex);
                            }
                  }
                  
                 //objRPC_Result=objPulse.P2P_Play(b_streams.getSelectedValue().toString().trim());
                 //String P2PURI=new String(objRPC_Result.CommandOutPut);

                 //P2PStream=P2PURI.substring(P2PURI.indexOf("'")+1,P2PURI.lastIndexOf("'"));
                 //objPlayStreams.DAVS_PlayP2PStreams(P2PStream);
               } else  {
                   JOptionPane.showMessageDialog(null,"This is not a P2P PULSE stream.\nPlease select the PULSE stream.","DAVS Message",
                   JOptionPane.ERROR_MESSAGE);
               }
            }
          }
       else {
              if (b_streams.isSelectionEmpty()) {
                 JOptionPane.showMessageDialog(null,"Please select the Live stream.");
             }
             else {
                if(objDAVSEngines.StreamAvailable(b_streams.getSelectedValue().toString().trim())==0)
                 {
                    LiveStream=b_streams.getSelectedValue().toString();
                    objPlayStreams.DAVS_PlayLiveStreams(LiveStream);
                  } else {
                    JOptionPane.showMessageDialog(null,"This is not a Live broadcast stream.\nPlease select the Live broadcast stream."
                            ,"DAVS Message",JOptionPane.ERROR_MESSAGE);
                  }
               }
           }
        }
    }//GEN-LAST:event_Play_LiveStreamMouseClicked

    private void vod_streamslistValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_vod_streamslistValueChanged
     if(!obj_vod.isEmpty())
      {
                String StreamID=(String)vod_streamslist.getSelectedValue();
                String strDescription = objDAVSServer.DAVS_GetVODStreamDescription(StreamID);
                Stream_Description.setText(strDescription);
                if(StreamID!=null)
                {
                    String DAVSImagePath=objDAVSServer.DAVS_GetSnapshot(StreamID);
                
                        if(DAVSImagePath.equalsIgnoreCase("")) {
                            davs_snapshot.setIcon(null);
                            davs_snapshot.setText("No Snapshot available.");
                        } else {
                              obj.SecureImageCopyFromDAVSServer(DAVSImagePath);
                              String strPath=System.getProperty("user.dir");
                              String ImageName=DAVSImagePath.substring(DAVSImagePath.lastIndexOf("/")+1, DAVSImagePath.length());
                              strPath=strPath+"\\DAVSSnapshot\\"+ImageName;
                              ImageIcon snapshot = new ImageIcon(strPath);
                              davs_snapshot.setText("");
                              davs_snapshot.setIcon(snapshot);
                        }
                }
       }
    }//GEN-LAST:event_vod_streamslistValueChanged

    private void Streaming_EnginesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Streaming_EnginesItemStateChanged
        if(evt.getSource()==Streaming_Engines) {
            if(Streaming_Engines.getSelectedItem().toString().equalsIgnoreCase("PULSE")) {
                if(Take_Snapshot.isEnabled()) {
                  Take_Snapshot.setSelected(false);
                  Take_Snapshot.setEnabled(false);
                }
            } else {
                Take_Snapshot.setEnabled(true);
            }
        }
    }//GEN-LAST:event_Streaming_EnginesItemStateChanged

    private void b_streamsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_b_streamsValueChanged
    if(!obj_live.isEmpty()) {
                  String StreamID=(String)b_streams.getSelectedValue();
                  String strDescription= objDAVSServer.DAVS_GetLiveStreamDescription(StreamID);
                  Stream_Description.setText(strDescription);
           }
    }//GEN-LAST:event_b_streamsValueChanged

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JMenu About_Menue;
    private javax.swing.JComboBox Audio_Codec;
    private javax.swing.JComboBox BitRate;
    private javax.swing.JButton Browse;
    private javax.swing.JMenuBar DAVS_Menue;
    private javax.swing.JProgressBar DAVS_Progress;
    private javax.swing.JSeparator DAVS_Statusbar_Seperator;
    private javax.swing.JLayeredPane DAVS_Streams;
    private javax.swing.JScrollPane Description;
    private javax.swing.JMenu File_Menue;
    private javax.swing.ButtonGroup Frame_Command_Group;
    private javax.swing.JRadioButton Frame_Import;
    private javax.swing.JRadioButton Frame_Start;
    private javax.swing.JRadioButton Frame_Stop;
    private javax.swing.JRadioButton Frame_Validate;
    private javax.swing.JButton Get_Broadcast_streams;
    private javax.swing.JButton Get_Vod_streams;
    private javax.swing.JLabel Labe_Video_Codec;
    private javax.swing.JLabel Label_Audio_Codec;
    private javax.swing.JLabel Label_BitRate;
    private javax.swing.JLabel Label_BroadcastStreams;
    private javax.swing.JLabel Label_Description;
    private javax.swing.JLabel Label_Media_Type;
    private javax.swing.JLabel Label_Streaming_engines;
    private javax.swing.JLabel Label_VODStreams;
    private javax.swing.JLabel Media_File;
    private javax.swing.JTextField Media_File_Path;
    private javax.swing.JMenuItem MenueClose;
    private javax.swing.ButtonGroup Menue_Command_Group;
    private javax.swing.JRadioButtonMenuItem Menue_Deport;
    private javax.swing.JRadioButtonMenuItem Menue_Import;
    private javax.swing.JSeparator Menue_Seperator;
    private javax.swing.JRadioButtonMenuItem Menue_Start;
    private javax.swing.JRadioButtonMenuItem Menue_Stop;
    private javax.swing.JRadioButtonMenuItem Menue_Validate;
    private javax.swing.JMenuItem Open;
    private javax.swing.JButton Play_LiveStream;
    private javax.swing.JButton Play_VOD;
    private javax.swing.JButton Remove_LiveStreams;
    private javax.swing.JButton Remove_VOD;
    private javax.swing.JButton Run_Command;
    private javax.swing.JSeparator Separator;
    private javax.swing.JLabel Snapshot;
    private javax.swing.JPanel StatusBar_Pannel;
    private javax.swing.JTextArea Stream_Description;
    private javax.swing.JComboBox Streaming_Engines;
    private javax.swing.JComboBox Streaming_Type;
    private javax.swing.JCheckBox Take_Snapshot;
    private javax.swing.JScrollPane VOD_Streams;
    private javax.swing.JComboBox Video_Codec;
    private javax.swing.JList b_streams;
    private javax.swing.JScrollPane broadcast_streams;
    private javax.swing.JLabel davs_snapshot;
    private javax.swing.JLabel davs_status_message;
    private javax.swing.JList vod_streamslist;
    // End of variables declaration//GEN-END:variables

    
    private JFileChooser MediaFileChooser;
    private ScriptResult objRPC_Result; // which stores the command result
    private DefaultListModel obj_vod = new DefaultListModel();
    private DefaultListModel obj_live = new DefaultListModel();
    private DAVS_About dialog;

    private static String DAVSRole;

    private boolean NeedsTranscoding;
    private boolean IsSDP=false;

    
    private String MediaStreamName=null;
    private String MediaStreamExtension=null;
    private String VodDescription=null;
    private String LiveDescription=null;
    private String URL=null;
    private String StreamingEngine=null;

    private StreamingEngines objDAVSEngines;
    private PlayStreams      objPlayStreams;
    private DAVSDatabase     objDAVSServer;
    private Live555          objLive555;
    private RTSP             objRTSP;
    private PulseP2PEngine   objPulse;
    private SecureMediaTransfer obj;
    
}
