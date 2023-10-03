package ir.aliyan.behsera.tool;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ir.aliyan.behsera.R;
import ir.aliyan.behsera.inter.InterClickFragment;
import ir.aliyan.behsera.server.GRetrofit;
import ir.aliyan.behsera.server.GetApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageToServer {


    public static final String kar = "kar";
    public static final String user = "user";
    public static final String behseraFolder = "behseraFolder";
    public static final String behseraFolderAds = "behseraFolderAds";
    public static final String ImageKalaOtherInsert = "ImageKalaOtherInsert";
    public static final String imageBordFolder = "imageBordFolder";
    public static final String kala = "kala";
    public static final String karAds = "karAds";
    InterClickFragment interBack;
    FragmentActivity context;
    String typeHub;
    ProgressBar progressBar;
    int w;
    int h;
    String id = "";
    String id_id = "";
    //     ActivityResultLauncher<Intent> activityResultLauncher= context.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//            Log.d("Lifefghddgfhhds", "ActivityAddDriver: onActivityResult" + result.getResultCode());
//            if (result.getResultCode() == RESULT_OK) {
//                selectedImage = String.valueOf(UCrop.getOutput(result.getData()));
//                interBack.callbackMethod(selectedImage);
//            } else if (result.getResultCode() == UCrop.RESULT_ERROR) {
//                final Throwable cropError = UCrop.getError(result.getData());
//            }
//
//        });
//    ActivityResultLauncher<String> resultLauncher =context. registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//            @Override
//            public void onActivityResult(Uri result) {
//                Log.d("fhgdshsdghj", "ImageToServer: onActivityResult" + result);
//                String s = "/1";
//                if (typeHub != null) {
//                    if (typeHub.equals(user)) {
//                        s = "/" + user;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
//                    }
//
//                    if (typeHub.equals(kar)) {
//                        s = "/" + kar;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
//                    }
//
//                    if (typeHub.equals(karAds)) {
//                        s = "/" + karAds;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(4, 2)
//                                .withMaxResultSize(1000, 500).getIntent(ImageToServer.this.context));
//                    }
//
//                    if (typeHub.equals(behseraFolder)) {
//                        s = "/" + behseraFolder;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(behseraFolderAds)) {
//                        s = "/" + behseraFolderAds;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(3, 1)
//                                .withMaxResultSize(1200, 400).getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(ImageKalaOtherInsert)) {
//                        s = "/" + ImageKalaOtherInsert;
//                        UCrop.Options options = new UCrop.Options();
//                        options.setMaxScaleMultiplier(50);
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withOptions(options)
//                                .getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(kala)) {
//                        s = "/" + kala;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600)
//                                .getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(imageBordFolder)) {
//                        s = "/" + imageBordFolder;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(2, 1)
//                                .withMaxResultSize(1000, 500)
//                                .getIntent(ImageToServer.this.context));
//                    }
//                }
//
//
//            }
//        });
//    ActivityResultLauncher<String> requestPermissionLauncher = context.registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
//            @Override
//            public void onActivityResult(Boolean result) {
//                if (result) {
//                    resultLauncher.launch("image/*");
//                } else {
//                    Toast.toastC(context, "  مجوز را رد کردید  ");
//
//                }
//            }
//        });
    ActivityResultLauncher<String> requestPermissionLauncher;
    ActivityResultLauncher<String> resultLauncher;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String selectedImage;

    public ImageToServer(FragmentActivity context) {
        this.context = context;
    }

    public void setContext() {
        activityResultLauncher = context.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            Log.d("Lifefghddgfhhds", "ActivityAddDriver: onActivityResult" + result.getResultCode());
            if (result.getResultCode() == RESULT_OK) {
                selectedImage = String.valueOf(UCrop.getOutput(result.getData()));
                interBack.callbackMethod(selectedImage);
            } else if (result.getResultCode() == UCrop.RESULT_ERROR) {
                final Throwable cropError = UCrop.getError(result.getData());
            }

        });
        resultLauncher = context.registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Log.d("fhgdshsdghj", "ImageToServer: onActivityResult" + result);
                String s = "/1";
                if (typeHub != null) {
                    if (typeHub.equals(user)) {
                        s = "/" + user;
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withAspectRatio(1, 1)
                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
                    }

                    if (typeHub.equals(kar)) {
                        s = "/" + kar;
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withAspectRatio(1, 1)
                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
                    }

                    if (typeHub.equals(karAds)) {
                        s = "/" + karAds;
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withAspectRatio(4, 2)
                                .withMaxResultSize(1000, 500).getIntent(ImageToServer.this.context));
                    }

                    if (typeHub.equals(behseraFolder)) {
                        s = "/" + behseraFolder;
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withAspectRatio(1, 1)
                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
                    }
                    if (typeHub.equals(behseraFolderAds)) {
                        s = "/" + behseraFolderAds;
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withAspectRatio(3, 1)
                                .withMaxResultSize(1200, 400).getIntent(ImageToServer.this.context));
                    }
                    if (typeHub.equals(ImageKalaOtherInsert)) {
                        s = "/" + ImageKalaOtherInsert;
                        UCrop.Options options = new UCrop.Options();
                        options.setMaxScaleMultiplier(50);
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withOptions(options)
                                .getIntent(ImageToServer.this.context));
                    }
                    if (typeHub.equals(kala)) {
                        s = "/" + kala;
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withAspectRatio(1, 1)
                                .withMaxResultSize(600, 600)
                                .getIntent(ImageToServer.this.context));
                    }
                    if (typeHub.equals(imageBordFolder)) {
                        s = "/" + imageBordFolder;
                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
                                .withAspectRatio(2, 1)
                                .withMaxResultSize(1000, 500)
                                .getIntent(ImageToServer.this.context));
                    }
                }


            }
        });
        requestPermissionLauncher = context.registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    resultLauncher.launch("image/*");
                } else {
                    Toast.toastC(context, "  مجوز را رد کردید  ");

                }
            }
        });

        Log.d("fgdshsfdgh", "ImageToServer: setContext" + (this.context == null));
    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d("fgdshsfdgh", "ImageToServer: onCreate" + (this.context == null));
//        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//            Log.d("Lifefghddgfhhds", "ActivityAddDriver: onActivityResult" + result.getResultCode());
//            if (result.getResultCode() == RESULT_OK) {
//                selectedImage = String.valueOf(UCrop.getOutput(result.getData()));
//                interBack.callbackMethod(selectedImage);
//            } else if (result.getResultCode() == UCrop.RESULT_ERROR) {
//                final Throwable cropError = UCrop.getError(result.getData());
//            }
//
//        });
//        resultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//            @Override
//            public void onActivityResult(Uri result) {
//                Log.d("fhgdshsdghj", "ImageToServer: onActivityResult" + result);
//                String s = "/1";
//                if (typeHub != null) {
//                    if (typeHub.equals(user)) {
//                        s = "/" + user;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
//                    }
//
//                    if (typeHub.equals(kar)) {
//                        s = "/" + kar;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
//                    }
//
//                    if (typeHub.equals(karAds)) {
//                        s = "/" + karAds;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(4, 2)
//                                .withMaxResultSize(1000, 500).getIntent(ImageToServer.this.context));
//                    }
//
//                    if (typeHub.equals(behseraFolder)) {
//                        s = "/" + behseraFolder;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600).getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(behseraFolderAds)) {
//                        s = "/" + behseraFolderAds;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(3, 1)
//                                .withMaxResultSize(1200, 400).getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(ImageKalaOtherInsert)) {
//                        s = "/" + ImageKalaOtherInsert;
//                        UCrop.Options options = new UCrop.Options();
//                        options.setMaxScaleMultiplier(50);
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withOptions(options)
//                                .getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(kala)) {
//                        s = "/" + kala;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(1, 1)
//                                .withMaxResultSize(600, 600)
//                                .getIntent(ImageToServer.this.context));
//                    }
//                    if (typeHub.equals(imageBordFolder)) {
//                        s = "/" + imageBordFolder;
//                        activityResultLauncher.launch(UCrop.of(result, Uri.fromFile(new File(ImageToServer.this.context.getFilesDir(), s + ".png")))
//                                .withAspectRatio(2, 1)
//                                .withMaxResultSize(1000, 500)
//                                .getIntent(ImageToServer.this.context));
//                    }
//                }
//
//
//            }
//        });
//        requestPermissionLauncher =registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
//            @Override
//            public void onActivityResult(Boolean result) {
//                if (result) {
//                    resultLauncher.launch("image/*");
//                } else {
//                    Toast.toastC(context, "  مجوز را رد کردید  ");
//
//                }
//            }
//        });
//
//    }

    public void chooseImage(String typeHub, InterClickFragment interBack) {
        Log.d("fgdshsfdgh", "ImageToServer: chooseImage" + (this.context == null));
        this.typeHub = typeHub;
        this.interBack = interBack;
        setImagePermissions();
    }

    public void chooseImage(String typeHub, String id, String id_id, InterClickFragment interBack) {
        this.typeHub = typeHub;
        this.interBack = interBack;
        this.id = id;
        this.id_id = id_id;
        setImagePermissions();
    }

    void setImagePermissions() {
        Log.d("Lifefhgdsh", "ActivityAddDriver: setImagePermissions");

        String preii;

        Intent intent = new Intent();
//        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setType("image/*");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("Lifefhgdsh", "ActivityAddDriver: setImagePermissions/");
            if (Build.VERSION.SDK_INT >= 33) {
                preii = Manifest.permission.READ_MEDIA_IMAGES;
                Log.d("Lifefhgdsh", "ActivityAddDriver: setImagePermissions//");
            } else {
                preii = Manifest.permission.READ_EXTERNAL_STORAGE;
                Log.d("Lifefhgdsh", "ActivityAddDriver: setImagePermissions///");
            }
            Log.d("fgdshsfdgh", "ImageToServer: setImagePermissions" + (this.context == null));
            Log.d("fgdshsfdgh", "ImageToServer: setImagePermissions" + (this.resultLauncher == null));
            if (ContextCompat.checkSelfPermission(context, preii) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(preii);
                ActivityCompat.requestPermissions(context, new String[]{preii, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
                Log.d("Lifefhgdsh", "ActivityAddDriver: setImagePermissions////");
            } else {
                resultLauncher.launch("image/*");
                Log.d("Lifefhgdsh", "ActivityAddDriver: setImagePermissions/////");
            }
        } else {
            Log.d("Lifefhgdsh", "ActivityAddDriver: setImagePermissions//////");
            resultLauncher.launch("image/*");
        }


    }

    public void sendImage(ProgressBar binding, InterClickFragment finishUpload) {
        String numberKar = "";
        binding.setVisibility(View.VISIBLE);

        if (typeHub.equals(user)) {

            numberKar = SharedPref.read(SharedPref.number, "");
        }
        if (typeHub.equals(kar)) {
            numberKar = SharedPref.read(SharedPref.idKarbar, "");
        }
        if (typeHub.equals(karAds)) {
            numberKar = SharedPref.read(SharedPref.idKarbar, "");
        }
        if (typeHub.equals(behseraFolder)) {
        }
        if (typeHub.equals(behseraFolderAds)) {

        }
        if (typeHub.equals(imageBordFolder)) {

        }
        if (typeHub.equals(ImageKalaOtherInsert)) {
            numberKar = id;
        }

        File file = new File(Uri.parse(selectedImage).getPath());

        RequestBody requestBody = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("sendimage", file.getName(), requestBody);
        RequestBody key = RequestBody.create(SharedPref.read(SharedPref.key, ""), MediaType.parse("text/plain"));
        RequestBody number = RequestBody.create(numberKar, MediaType.parse("text/plain"));
        RequestBody type = RequestBody.create(typeHub, MediaType.parse("text/plain"));
        RequestBody id_id = RequestBody.create(this.id_id, MediaType.parse("text/plain"));


        Log.d("sghfjfsgj", "ChooseImage: sendImage" + "--" + file.getName() + "---" + key + "----" + numberKar + "-----" + typeHub);
        Call<GetApi> call = GRetrofit.retrofit().addCoverProfile(
                filePart,
                key,
                number,
                type,
                id_id
        );

        call.enqueue(new Callback<GetApi>() {
            @Override
            public void onResponse(Call<GetApi> call, Response<GetApi> response) {
                finishUpload.callbackMethod("");
                binding.setVisibility(View.GONE);
                GetApi getApi = response.body();
                Log.d("Lifesfgdh", "ActivityInsertName: onResponse" + getApi);
                if (getApi != null) {
                    if (getApi.getString1().equals("110")) {
                        finishUpload.callbackMethod(getApi.getString2());
                    }
                } else {
                    finishUpload.callbackMethod("");
                    Toast.toastC(context, "  خطا 2!!  ");
                }
            }

            @Override
            public void onFailure(Call<GetApi> call, Throwable t) {
                binding.setVisibility(View.GONE);
                Toast.toastC(context, "  خطا 1!  ");
                Log.d("Lifesfgdh", "FmWorkFace: onFailure");
            }
        });
    }

    public void prepraImage(String uri, ImageView imageView, InterClickFragment clickFragment) {
        Glide.with(context)
                .load(uri)
                .centerCrop()
                .placeholder(R.color.colorZir)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        clickFragment.callbackMethod("");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        clickFragment.callbackMethod("");
                        return false;
                    }
                })

                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                        imageView.setDrawingCacheEnabled(true);
                        BitmapDrawable draw = (BitmapDrawable) imageView.getDrawable();
                        Bitmap bitmap = draw.getBitmap();

                        FileOutputStream outStream = null;
                        File dir = new File(context.getFilesDir() + "/imageTruckCar");
                        dir.mkdirs();
                        String fileName = "1.png";
                        File outFile = new File(dir, fileName);
                        try {
                            outStream = new FileOutputStream(outFile);
                            selectedImage = outFile.getPath();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        Log.d("Lifergdshd", "ActivityAddDriver: onResourceReady>" + selectedImage);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                        try {
                            outStream.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            outStream.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        clickFragment.callbackMethod("");
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        clickFragment.callbackMethod("");
                    }

                });
    }
}
