from rest_framework import serializers
from .models import SoundFeature

class SoundSerializer(serializers.ModelSerializer):
    class Meta:
        model = SoundFeature
        fields = '__all__'